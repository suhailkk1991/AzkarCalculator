package com.syntaxsolutions.azkarcalculator.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.application.BaseActivity;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathDetailsTable;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathHeaderTable;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathHeaderTable$Table;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrDetailsTable;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrHeaderTable;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrHeaderTable$Table;
import com.syntaxsolutions.azkarcalculator.constants.Constants;
import com.syntaxsolutions.azkarcalculator.dto.EventDto;
import com.syntaxsolutions.azkarcalculator.view.activityInteractor.ZikrCountInteractor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ZikrCountActivity extends BaseActivity {
    private TextView counter;
    private Vibrator vibrator;
    private ViewStub vieStub;
    private TextView txtZikr;
    private EventDto eventDto;
    private long totalZikrInTheCurrentContext;
    private ZikrCountInteractor zikrCountInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentExtra();
        initView();

    }

    private void setCounterButton() {
        ImageView btnCount = (ImageView) findViewById(R.id.btnCount);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long totalCount = Long.parseLong(counter.getText().toString().trim());
                totalCount++;
                totalZikrInTheCurrentContext++;
                counter.setText("" + totalCount);
                vibrator.vibrate(Constants.VIBRATOR);
            }
        });
    }


    private void getIntentExtra() {
        Gson gson = new Gson();
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("navigationData")) {
            String intentExtra = getIntent().getStringExtra("navigationData");
            eventDto = gson.fromJson(intentExtra, EventDto.class);
        } else {
            eventDto = new EventDto();
        }
    }


    private void initView() {
        vieStub = (ViewStub) findViewById(R.id.contentLayout);
        vieStub.setLayoutResource(R.layout.app_bar_home);
        zikrCountInteractor = new ZikrCountInteractor(ZikrCountActivity.this);
        vieStub.inflate();
        counter = (TextView) findViewById(R.id.txtCount);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        txtZikr = (TextView) findViewById(R.id.txtZikr);
        setDefaultValue();
        setFloatingButton();
        setCounterButton();
        setToolBar();
    }

    private void setDefaultValue() {
        if (!eventDto.getFromScreen().equalsIgnoreCase("")) {
            counter.setText("" + eventDto.getCurrentCount());
            txtZikr.setText(eventDto.getName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear) {
            counter.setText("0");
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eventDto.getFromScreen().equalsIgnoreCase(Constants.ZIKR_SCREEN_NAME)) {
                    zikrCountInteractor.saveZikrDetails(eventDto.getId(), totalZikrInTheCurrentContext);
                    zikrCountInteractor.updateZikrHeaderTable(eventDto.getId(), Long.parseLong(counter.getText().toString()));
                    Toast.makeText(ZikrCountActivity.this, "Zikr saved successfully", Toast.LENGTH_SHORT).show();
                    zikrCountInteractor.proceedNavigationToZikr();
                    finish();
                } else {
                    zikrCountInteractor.saveSwalathDetails(eventDto.getId(), totalZikrInTheCurrentContext);
                    zikrCountInteractor.updateSwalathHeaderTable(eventDto.getId(), Long.parseLong(counter.getText().toString()));
                    Toast.makeText(ZikrCountActivity.this, "Swalath saved successfully", Toast.LENGTH_SHORT).show();
                    zikrCountInteractor.proceedNavigationToSwalath();
                    finish();
                }
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}
