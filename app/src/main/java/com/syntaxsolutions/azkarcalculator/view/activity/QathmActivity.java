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
import com.syntaxsolutions.azkarcalculator.base.database.QathmHeaderTable;
import com.syntaxsolutions.azkarcalculator.helpers.RecyclerViewTouchHelper;
import com.syntaxsolutions.azkarcalculator.service.QathmService;
import com.syntaxsolutions.azkarcalculator.service.QathmServiceImpl;
import com.syntaxsolutions.azkarcalculator.view.activityInteractor.QathmActivityInteractor;
import com.syntaxsolutions.azkarcalculator.view.adapter.QathmListAdapter;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by suhail.kk on 29-09-2016.
 */
public class QathmActivity extends BaseActivity {
    ViewStub vieStub;
    RecyclerView lstQathmRecyclerView;
    List<QathmHeaderTable> lstAllQathm;
    QathmListAdapter qathmListAdapter;
    ImageView imgStartCount;
    QathmHeaderTable selectedHeaderTableForEdit;
    private QathmService qathmService = new QathmServiceImpl();
    private QathmActivityInteractor qathmActivityInteractor;
    EditQathmCallBackHandler editQathmCallBackHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setRecyclerViewAnimations();
    }

    private void setRecyclerViewAnimations() {
        ItemTouchHelper.Callback callback = new RecyclerViewTouchHelper(qathmListAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(lstQathmRecyclerView);
    }

    protected void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Qathm");
    }


    protected void setFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupForNewQathmCreation();
            }
        });
    }

    private void initView() {
        vieStub = (ViewStub) findViewById(R.id.contentLayout);
        lstQathmRecyclerView = (RecyclerView) findViewById(R.id.lstListZikrItems);
        imgStartCount = (ImageView) findViewById(R.id.imgStartCount);
        qathmActivityInteractor = new QathmActivityInteractor(QathmActivity.this);
        vieStub.setLayoutResource(R.layout.qathm_activity);

        vieStub.inflate();
        setToolBar();
        setFloatingButton();


    }

    private void showPopupForNewQathmCreation() {
        Intent intent = new Intent(getApplicationContext(), QathmCreationActivity.class);
        startActivity(intent);

    }

    private void setAdapter() {
        RecyclerView.LayoutManager mLayoutManager;
        lstAllQathm = qathmService.getAll();
        if (lstAllQathm == null || lstAllQathm.size() <= 0) {
            Toast.makeText(getApplication(), "No data available", Toast.LENGTH_LONG).show();
            return;
        } else {
            lstQathmRecyclerView = (RecyclerView) findViewById(R.id.lstListQathmItems);
            lstQathmRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            lstQathmRecyclerView.setLayoutManager(mLayoutManager);
            qathmListAdapter = new QathmListAdapter(lstAllQathm, lstQathmRecyclerView, QathmActivity.this);
            lstQathmRecyclerView.setAdapter(qathmListAdapter);
        }
        setRecyclerViewAnimations();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();

    }

    public void onClickCalled(int position) {
        qathmActivityInteractor.proceedNavigation(lstAllQathm.get(position));
    }

    public void onClickForEdit(int position) {
        editQathmCallBackHandler = new EditQathmCallBackHandler();
        qathmActivityInteractor.deleteObservers();
        qathmActivityInteractor.addObserver(editQathmCallBackHandler);
        qathmActivityInteractor.showPopUpForEdit(lstAllQathm.get(position));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    class EditQathmCallBackHandler implements Observer {
        @Override
        public void update(Observable observable, Object data) {
            String response = (String) data;
            if (response.equalsIgnoreCase("success")) {
                setAdapter();
                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_LONG).show();
            }
        }
    }
}

