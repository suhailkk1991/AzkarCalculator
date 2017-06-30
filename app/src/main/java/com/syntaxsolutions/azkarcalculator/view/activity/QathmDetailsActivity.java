package com.syntaxsolutions.azkarcalculator.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.database.QathmDetailsTable;
import com.syntaxsolutions.azkarcalculator.dto.QathmHeaderDto;
import com.syntaxsolutions.azkarcalculator.helpers.RecyclerViewTouchHelper;
import com.syntaxsolutions.azkarcalculator.service.QathmDetailsService;
import com.syntaxsolutions.azkarcalculator.service.QathmDetailsServiceImpl;
import com.syntaxsolutions.azkarcalculator.view.adapter.QathmDetailsListAdapter;
import java.util.List;

/**
 * Created by suhail.kk on 18-11-2016.
 */
public class QathmDetailsActivity extends AppCompatActivity {

    List<QathmDetailsTable> lstQathmDetails;
    QathmHeaderDto qathmHeaderDto;
    RecyclerView lstQathamRecyclerView;
    TextView txtQathmName;
    TextView txtStartDate;
    TextView txtEndDate;
    TextView txtSurathName;
    TextView txtAyathNumber;
    QathmDetailsListAdapter zikrDetailsListAdapter;
    QathmDetailsService qathmDetailsService=new QathmDetailsServiceImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qathm_details_layout);
        getIntentExtra();
        initView();
        setData();
        setAdapter();
        setToolBar();
        setRecyclerViewAnimations();

    }

    private void getIntentExtra() {

        Gson gson = new Gson();
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("navigationData")) {
            String intentExtra = getIntent().getStringExtra("navigationData");
            qathmHeaderDto = gson.fromJson(intentExtra, QathmHeaderDto.class);
        } else {
            qathmHeaderDto = new QathmHeaderDto();
        }
    }

    private void setRecyclerViewAnimations() {
        ItemTouchHelper.Callback callback = new RecyclerViewTouchHelper(zikrDetailsListAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(lstQathamRecyclerView);
    }

    private void initView() {
        txtQathmName = (TextView) findViewById(R.id.txtQathmNameVal);
        txtStartDate = (TextView) findViewById(R.id.txtqathmStartDateVal);
        txtEndDate = (TextView) findViewById(R.id.txtQathmEndDateVal);
        txtSurathName = (TextView) findViewById(R.id.txtSurathNameHeadertVal);
        txtAyathNumber = (TextView) findViewById(R.id.txtAyathNumberHeaderVal);
        lstQathamRecyclerView = (RecyclerView) findViewById(R.id.lstQathmItems);

    }

    private void setData() {
        txtQathmName.setText(qathmHeaderDto.getName());
        txtStartDate.setText(qathmHeaderDto.getStartDate());
        txtEndDate.setText(qathmHeaderDto.getEnddate());
        txtSurathName.setText(qathmHeaderDto.getSurath());
        txtAyathNumber.setText("" + qathmHeaderDto.getAyath());
    }


    private void setAdapter() {
        RecyclerView.LayoutManager mLayoutManager;
        lstQathmDetails = qathmDetailsService.getAll(qathmHeaderDto.getId());
        if (lstQathmDetails == null || lstQathmDetails.size() <= 0) {
            return;
        } else {
            lstQathamRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            lstQathamRecyclerView.setLayoutManager(mLayoutManager);
            zikrDetailsListAdapter = new QathmDetailsListAdapter(lstQathmDetails, lstQathamRecyclerView, QathmDetailsActivity.this);
            lstQathamRecyclerView.setAdapter(zikrDetailsListAdapter);
        }

    }

    protected void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Qathm Details");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}



