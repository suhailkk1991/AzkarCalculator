package com.syntaxsolutions.azkarcalculator.base.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.view.activity.AfterSwalathAzkarCounter;
import com.syntaxsolutions.azkarcalculator.view.activity.HadhadhActivity;
import com.syntaxsolutions.azkarcalculator.view.activity.HomeActivity;
import com.syntaxsolutions.azkarcalculator.view.activity.ZikrCountActivity;
import com.syntaxsolutions.azkarcalculator.view.activity.QathmActivity;
import com.syntaxsolutions.azkarcalculator.view.activity.SwalathActivity;
import com.syntaxsolutions.azkarcalculator.view.activity.ZikrActivity;

/**
 * Created by suhail.kk on 05-09-2016.
 */
public abstract class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        setNavigationDrawer();
    }

    protected void setNavigationDrawer() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected abstract void setToolBar();

    protected abstract void setFloatingButton();

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_zikr) {
            Intent intent = new Intent(getApplicationContext(), ZikrActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_swalath) {
            Intent intent = new Intent(getApplicationContext(), SwalathActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_qathm) {
            Intent intent = new Intent(getApplicationContext(), QathmActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_counter_after_swalath) {
            Intent intent = new Intent(getApplicationContext(), AfterSwalathAzkarCounter.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_hadhadh) {
            Intent intent = new Intent(getApplicationContext(), HadhadhActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
