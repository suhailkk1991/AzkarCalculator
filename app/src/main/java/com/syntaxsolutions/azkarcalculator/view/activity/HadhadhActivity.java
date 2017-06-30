package com.syntaxsolutions.azkarcalculator.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.ViewStub;

import com.rd.PageIndicatorView;
import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.application.BaseActivity;
import com.syntaxsolutions.azkarcalculator.view.adapter.SlideImageAdapter;

import java.util.ArrayList;

/**
 * Created by suhail.kk on 28-11-2016.
 */
public class HadhadhActivity extends BaseActivity {
    private static ViewPager mPager;
    private static final Integer[] IMAGES = {R.drawable.one, R.drawable.two, R.drawable.three};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    ViewStub vieStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        init();
    }

    protected void setFloatingButton() {

    }

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

    private void initView() {
        vieStub = (ViewStub) findViewById(R.id.contentLayout);
        vieStub.setLayoutResource(R.layout.hadhadh__layout);
        vieStub.inflate();
        setToolBar();
        setFloatingButton();

    }

    private void init() {
        for (int index = 0; index < IMAGES.length; index++)
            ImagesArray.add(IMAGES[index]);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlideImageAdapter(HadhadhActivity.this, ImagesArray));
        PageIndicatorView pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(mPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
}