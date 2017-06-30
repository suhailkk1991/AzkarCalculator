package com.syntaxsolutions.azkarcalculator.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.application.BaseActivity;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrHeaderTable;
import com.syntaxsolutions.azkarcalculator.dto.ZikrHeaderDto;
import com.syntaxsolutions.azkarcalculator.helpers.RecyclerViewTouchHelper;
import com.syntaxsolutions.azkarcalculator.service.ZikrActivityService;
import com.syntaxsolutions.azkarcalculator.service.ZikrActivityServiceImpl;
import com.syntaxsolutions.azkarcalculator.view.adapter.ZikrListAdapter;

import java.util.List;

/**
 * Created by suhail.kk on 29-09-2016.
 */
public class ZikrActivity extends BaseActivity {
    ViewStub vieStub;
    RecyclerView lstZikrRecyclerView;
    List<ZikrHeaderTable> lstAllZikr;
    ZikrListAdapter zikrListAdapter;
    ImageView imgStartCount;
    FloatingActionButton fab;
    ZikrActivityService zikrActivityService=new ZikrActivityServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    /**
     * Animation for recycler view
     */
    private void setRecyclerViewAnimations() {
        ItemTouchHelper.Callback callback = new RecyclerViewTouchHelper(zikrListAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(lstZikrRecyclerView);
    }

    /**
     * setting floating button
     */
    protected void setFloatingButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupForNewZikrCreation();
            }
        });
    }

    /**
     * set tool bar
     */
    protected void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Zikr");

    }

    /**
     * initialize the view
     */
    private void initView() {
        vieStub = (ViewStub) findViewById(R.id.contentLayout);
        lstZikrRecyclerView = (RecyclerView) findViewById(R.id.lstListZikrItems);
        imgStartCount = (ImageView) findViewById(R.id.imgStartCount);
        vieStub.setLayoutResource(R.layout.zikr_activity_layout);

        vieStub.inflate();
        setToolBar();
        setFloatingButton();


    }

    /**
     * pop up for new zikr creation
     */
    private void showPopupForNewZikrCreation() {
        Intent intent = new Intent(getApplicationContext(), ZikrCreationActivity.class);
        startActivity(intent);

    }

    /**
     * set adapter
     */
    private void setAdapter() {
        RecyclerView.LayoutManager mLayoutManager;
        lstAllZikr = zikrActivityService.getAll();
        if (lstAllZikr == null || lstAllZikr.size() <= 0) {
            Toast.makeText(getApplication(), "No data available", Toast.LENGTH_LONG).show();
            return;
        } else {
            lstZikrRecyclerView = (RecyclerView) findViewById(R.id.lstListZikrItems);
            lstZikrRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            lstZikrRecyclerView.setLayoutManager(mLayoutManager);
            zikrListAdapter = new ZikrListAdapter(lstAllZikr, lstZikrRecyclerView, ZikrActivity.this);
            lstZikrRecyclerView.setAdapter(zikrListAdapter);
        }
        setRecyclerViewAnimations();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    public void onClickCalled(int position) {
        Intent intent = new Intent(getApplicationContext(), ZikrDetailsActivity.class);
        ZikrHeaderTable zikrHeaderTable = new ZikrHeaderTable();
        zikrHeaderTable = lstAllZikr.get(position);
        ZikrHeaderDto zikrHeaderDto = new ZikrHeaderDto();
        zikrHeaderDto.setId(zikrHeaderTable.getId());
        zikrHeaderDto.setName(zikrHeaderTable.getName());
        zikrHeaderDto.setStartDate(zikrHeaderTable.getStartDate());
        zikrHeaderDto.setEnddate(zikrHeaderTable.getEnddate());
        zikrHeaderDto.setCount(zikrHeaderTable.getCount());
        zikrHeaderDto.setTarget(zikrHeaderTable.getTarget());
        Gson gson = new Gson();
        String json = gson.toJson(zikrHeaderDto);
        intent.putExtra("navigationData", json);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
