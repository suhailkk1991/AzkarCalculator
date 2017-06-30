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

import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.application.BaseActivity;

/**
 * Created by suhail.kk on 05-09-2016.
 */
public class AfterSwalathAzkarCounter extends BaseActivity {
    TextView counter;
    Vibrator vibrator;
    ViewStub vieStub;
    TextView txtZikr;
    int zikrType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    private void initView() {
        vieStub = (ViewStub) findViewById(R.id.contentLayout);
        vieStub.setLayoutResource(R.layout.app_bar_home);
        vieStub.inflate();
        counter = (TextView) findViewById(R.id.txtCount);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        txtZikr = (TextView) findViewById(R.id.txtZikr);
        setToolBar();
        setFloatingButton();
        setCounterButton();
        setInitValues();
    }

    protected void setFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
    }

    private void setInitValues() {
        counter.setText("0");
        zikrType = 1;
        txtZikr.setText("سبحانالله");
        txtZikr.setTextSize(30);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear) {
            counter.setText("0");
            zikrType = 1;
            txtZikr.setText("سبحانالله");
            txtZikr.setTextSize(30);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    protected void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Zikr After Swalath");
    }



    private void setCounterButton() {
        ImageView btnCount = (ImageView) findViewById(R.id.btnCount);
        btnCount.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            long totalCount = Long.parseLong(counter.getText().toString().trim());
                                            if (totalCount == 33 || zikrType == 4) {
                                                totalCount = 0;
                                                switch (zikrType) {
                                                    case 1:
                                                        zikrType = 2;
                                                        txtZikr.setText("الحمدلله");
                                                        txtZikr.setTextSize(30);
                                                        break;
                                                    case 2:
                                                        zikrType = 3;
                                                        txtZikr.setText("الله أكبر");
                                                        txtZikr.setTextSize(30);
                                                        break;
                                                    case 3:
                                                        zikrType = 4;
                                                        txtZikr.setText("لا اِلهَ اِلَّا اللّهُ وَحْدَهُ لا شَرِيكَ لَهُ ، لَهُ الْمُلْكُ وَ لَهُ الْحَمْدُ وَ هُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ");
                                                        txtZikr.setTextSize(15);
                                                        break;
                                                    case 4:
                                                        zikrType = 1;
                                                        totalCount = -1;
                                                        txtZikr.setText("سبحانالله");
                                                        txtZikr.setTextSize(30);
                                                        break;


                                                }
                                            }
                                            totalCount++;
                                            counter.setText("" + totalCount);
                                            vibrator.vibrate(50);
                                        }
                                    }

        );
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
