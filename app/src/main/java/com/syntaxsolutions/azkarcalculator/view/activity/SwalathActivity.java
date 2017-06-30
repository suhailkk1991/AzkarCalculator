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

import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.application.BaseActivity;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathHeaderTable;
import com.syntaxsolutions.azkarcalculator.helpers.RecyclerViewTouchHelper;
import com.syntaxsolutions.azkarcalculator.service.SwalathActivityService;
import com.syntaxsolutions.azkarcalculator.service.SwalathActivityServiceImpl;
import com.syntaxsolutions.azkarcalculator.view.activityInteractor.SwalathActivityInteractor;
import com.syntaxsolutions.azkarcalculator.view.adapter.SwalathListAdapter;

import java.util.List;

/**
 * Created by suhail.kk on 29-09-2016.
 */
public class SwalathActivity extends BaseActivity {
    private ViewStub vieStub;
    private RecyclerView lstswalathRecyclerView;
    private List<SwalathHeaderTable> lstAllSwalath;
    private SwalathListAdapter swalathListAdapter;
    private ImageView imgStartCount;
    private SwalathActivityService swalathActivityService = new SwalathActivityServiceImpl();
    private SwalathActivityInteractor swalathActivityInteractor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void setRecyclerViewAnimations() {
        ItemTouchHelper.Callback callback = new RecyclerViewTouchHelper(swalathListAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(lstswalathRecyclerView);
    }

    protected void setFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupForNewSwalathCreation();
            }
        });
    }

    protected void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Swalath");
    }

    private void initView() {
        vieStub = (ViewStub) findViewById(R.id.contentLayout);
        lstswalathRecyclerView = (RecyclerView) findViewById(R.id.lstListSwalathItems);
        imgStartCount = (ImageView) findViewById(R.id.imgStartCount);
        swalathActivityInteractor = new SwalathActivityInteractor(SwalathActivity.this);
        vieStub.setLayoutResource(R.layout.swalath_activity_layout);
        vieStub.inflate();
        setToolBar();
        setFloatingButton();
    }

    private void showPopupForNewSwalathCreation() {
        Intent intent = new Intent(getApplicationContext(), SwalathCreationActivity.class);
        startActivity(intent);

    }

    private void setAdapter() {
        RecyclerView.LayoutManager mLayoutManager;
        lstAllSwalath = swalathActivityService.getAll();
        if (lstAllSwalath == null || lstAllSwalath.size() <= 0) {
            Toast.makeText(getApplication(), "No data available", Toast.LENGTH_LONG).show();
            return;
        } else {
            lstswalathRecyclerView = (RecyclerView) findViewById(R.id.lstListSwalathItems);
            lstswalathRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            lstswalathRecyclerView.setLayoutManager(mLayoutManager);
            swalathListAdapter = new SwalathListAdapter(lstAllSwalath, lstswalathRecyclerView, SwalathActivity.this);
            lstswalathRecyclerView.setAdapter(swalathListAdapter);
        }
        setRecyclerViewAnimations();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    public void onClickCalled(int position) {
        swalathActivityInteractor.navigateToListingScreen(lstAllSwalath.get(position));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
}


