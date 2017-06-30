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
import android.widget.Toast;

import com.google.gson.Gson;
import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrDetailsTable;
import com.syntaxsolutions.azkarcalculator.dto.ZikrHeaderDto;
import com.syntaxsolutions.azkarcalculator.helpers.RecyclerViewTouchHelper;
import com.syntaxsolutions.azkarcalculator.service.ZikrDetailsService;
import com.syntaxsolutions.azkarcalculator.service.ZikrDetailsServiceImpl;
import com.syntaxsolutions.azkarcalculator.view.activityInteractor.ZikrDetailsInteractor;
import com.syntaxsolutions.azkarcalculator.view.adapter.ZikrDetailsListAdapter;

import java.util.List;

/**
 * Created by suhail.kk on 16-11-2016.
 */
public class ZikrDetailsActivity extends AppCompatActivity {
    List<ZikrDetailsTable> lstZikrDetails;
    ZikrHeaderDto zikrHeaderDto;
    RecyclerView lstZikrRecyclerView;
    TextView txtZikrName;
    TextView txtzikrStartDate;
    TextView txtZikrendDate;
    TextView txtTarget;
    ZikrDetailsListAdapter zikrDetailsListAdapter;
    ZikrDetailsService zikrDetailsService = new ZikrDetailsServiceImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zikr_details_layout);
        getIntentExtra();
        initView();
        setData();
        setAdapter();
        setToolBar();
        setRecyclerViewAnimations();
    }

    private void setRecyclerViewAnimations() {
        ItemTouchHelper.Callback callback = new RecyclerViewTouchHelper(zikrDetailsListAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(lstZikrRecyclerView);
    }

    private void getIntentExtra() {

        Gson gson = new Gson();
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("navigationData")) {
            String intentExtra = getIntent().getStringExtra("navigationData");
            zikrHeaderDto = gson.fromJson(intentExtra, ZikrHeaderDto.class);
        } else {
            zikrHeaderDto = new ZikrHeaderDto();
        }
    }

    private void initView() {
        txtZikrName = (TextView) findViewById(R.id.txtZikrNameVal);
        txtzikrStartDate = (TextView) findViewById(R.id.txtZikrStartDateVal);
        txtZikrendDate = (TextView) findViewById(R.id.txtZikrEndDateVal);
        txtTarget = (TextView) findViewById(R.id.txtZikrTargetVal);
        lstZikrRecyclerView = (RecyclerView) findViewById(R.id.lstListZikrItems);

    }

    private void setData() {
        txtZikrName.setText(zikrHeaderDto.getName());
        txtzikrStartDate.setText(zikrHeaderDto.getStartDate());
        txtZikrendDate.setText(zikrHeaderDto.getEnddate());
        txtTarget.setText("" + zikrHeaderDto.getTarget());
    }


    private void setAdapter() {
        RecyclerView.LayoutManager mLayoutManager;
        lstZikrDetails = zikrDetailsService.getAll(zikrHeaderDto.getId());
        if (lstZikrDetails == null || lstZikrDetails.size() <= 0) {
            Toast.makeText(getApplication(), "No data available", Toast.LENGTH_LONG).show();
            return;
        } else {
            lstZikrRecyclerView = (RecyclerView) findViewById(R.id.lstListZikrItems);
            lstZikrRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            lstZikrRecyclerView.setLayoutManager(mLayoutManager);
            zikrDetailsListAdapter = new ZikrDetailsListAdapter(lstZikrDetails, lstZikrRecyclerView, ZikrDetailsActivity.this);
            lstZikrRecyclerView.setAdapter(zikrDetailsListAdapter);
        }

    }

    protected void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Zikr Details");
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
        getMenuInflater().inflate(R.menu.menu_new_zikr_creation, menu);
        MenuItem menuItem = menu.findItem(R.id.action_save);
        menuItem.setTitle("Start");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            ZikrDetailsInteractor zikrDetaailsInteractor = new ZikrDetailsInteractor(ZikrDetailsActivity.this);
            zikrDetaailsInteractor.startZikrCounting(zikrHeaderDto);

        }
        return super.onOptionsItemSelected(item);
    }

}
