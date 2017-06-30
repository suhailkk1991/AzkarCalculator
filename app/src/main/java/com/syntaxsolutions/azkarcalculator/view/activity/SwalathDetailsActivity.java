package com.syntaxsolutions.azkarcalculator.view.activity;

import android.content.Intent;
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
import com.syntaxsolutions.azkarcalculator.base.database.SwalathDetailsTable;
import com.syntaxsolutions.azkarcalculator.constants.Constants;
import com.syntaxsolutions.azkarcalculator.dto.EventDto;
import com.syntaxsolutions.azkarcalculator.dto.ZikrHeaderDto;
import com.syntaxsolutions.azkarcalculator.helpers.RecyclerViewTouchHelper;
import com.syntaxsolutions.azkarcalculator.service.SwalathDetailsService;
import com.syntaxsolutions.azkarcalculator.service.SwalathDetailsServiceImpl;
import com.syntaxsolutions.azkarcalculator.view.adapter.SwalathDetailsListAdapter;

import java.util.List;

/**
 * Created by suhail.kk on 16-11-2016.
 */
public class SwalathDetailsActivity extends AppCompatActivity {
    List<SwalathDetailsTable> lstSwalathDetails;
    ZikrHeaderDto swalathHeaderDto;
    RecyclerView lstswalathRecyclerView;
    TextView txtSwalathName;
    TextView txtSwalathStartDate;
    TextView txtSwalathendDate;
    TextView txtTarget;
    TextView txtCurrentTotal;
    SwalathDetailsListAdapter swalathDetailsListAdapter;
    SwalathDetailsService swalathDetailsService=new SwalathDetailsServiceImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swalath_details_layout);
        getIntentExtra();
        initView();
        setData();
        setAdapter();
        setToolBar();
        setRecyclerViewAnimations();
    }
    private  void setRecyclerViewAnimations()
    {
        ItemTouchHelper.Callback callback = new RecyclerViewTouchHelper(swalathDetailsListAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(lstswalathRecyclerView);
    }
    private void getIntentExtra() {

        Gson gson = new Gson();
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("navigationData")) {
            String intentExtra = getIntent().getStringExtra("navigationData");
            swalathHeaderDto = gson.fromJson(intentExtra, ZikrHeaderDto.class);
        } else {
            swalathHeaderDto = new ZikrHeaderDto();
        }
    }

    private void initView() {
        txtSwalathName = (TextView) findViewById(R.id.txtSwalathNameVal);
        txtSwalathStartDate = (TextView) findViewById(R.id.txtSwalathStartDateVal);
        txtSwalathendDate = (TextView) findViewById(R.id.txtSwalathEndDateVal);
        txtTarget = (TextView) findViewById(R.id.txtSwalathTargetVal);
        lstswalathRecyclerView = (RecyclerView) findViewById(R.id.lstListSwalathItems);

    }

    private void setData() {
        txtSwalathName.setText(swalathHeaderDto.getName());
        txtSwalathStartDate.setText(swalathHeaderDto.getStartDate());
        txtSwalathendDate.setText(swalathHeaderDto.getEnddate());
        txtTarget.setText("" + swalathHeaderDto.getTarget());
    }


    private void setAdapter() {
        RecyclerView.LayoutManager mLayoutManager;
        lstSwalathDetails =swalathDetailsService.getAll(swalathHeaderDto.getId());
        if (lstSwalathDetails == null || lstSwalathDetails.size() <= 0) {
            Toast.makeText(getApplication(), "No data available", Toast.LENGTH_LONG).show();
            return;
        } else {
            lstswalathRecyclerView = (RecyclerView) findViewById(R.id.lstListSwalathItems);
            lstswalathRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            lstswalathRecyclerView.setLayoutManager(mLayoutManager);
            swalathDetailsListAdapter = new SwalathDetailsListAdapter(lstSwalathDetails, lstswalathRecyclerView, SwalathDetailsActivity.this);
            lstswalathRecyclerView.setAdapter(swalathDetailsListAdapter);
        }

    }

    protected void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Swalath Details");
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
            Intent intent = new Intent(getApplicationContext(), ZikrCountActivity.class);
            EventDto eventDto = new EventDto();
            eventDto.setCurrentCount(swalathHeaderDto.getCount());
            eventDto.setId(swalathHeaderDto.getId());
            eventDto.setFromScreen(Constants.SWALATH_SCREEN_NAME);
            eventDto.setName(swalathHeaderDto.getName());
            Gson gson = new Gson();
            String json = gson.toJson(eventDto);
            intent.putExtra("navigationData", json);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
