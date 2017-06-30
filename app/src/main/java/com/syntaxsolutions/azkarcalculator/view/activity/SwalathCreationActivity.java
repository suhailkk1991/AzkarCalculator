package com.syntaxsolutions.azkarcalculator.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.view.activityInteractor.SwalathCreationInteractor;
import com.syntaxsolutions.azkarcalculator.view.fragment.DatePickerFragment;

/**
 * Created by suhail.kk on 30-09-2016.
 */
public class SwalathCreationActivity extends AppCompatActivity {
    EditText edtName, edtStartDate, edtEndDate, edtTarget;
    CoordinatorLayout cordinatorlayout;
    SwalathCreationInteractor swalathCreationInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zikr_creation_layout);
        initView();
        setToolBar();
        edtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setEdiText(edtStartDate);
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setEdiText(edtEndDate);
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_zikr_creation, menu);
        return true;
    }

    protected void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        getSupportActionBar().setTitle("New Swalath Creation");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        swalathCreationInteractor = new SwalathCreationInteractor(getApplicationContext());
        edtName = (EditText) findViewById(R.id.txtName);
        edtStartDate = (EditText) findViewById(R.id.txtStartDate);
        edtEndDate = (EditText) findViewById(R.id.txtEndDate);
        edtTarget = (EditText) findViewById(R.id.txtTarget);
        cordinatorlayout = (CoordinatorLayout) findViewById(R.id.cordinatorlayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            proceedSave();

        }
        return super.onOptionsItemSelected(item);
    }

    private void proceedSave() {
        StringBuilder saveResponce = new StringBuilder();
        String name = edtName.getText().toString().trim();
        String startDate = edtStartDate.getText().toString().trim();
        String endDate = edtEndDate.getText().toString().trim();
        String target = edtTarget.getText().toString().trim();
        saveResponce = swalathCreationInteractor.save(name, startDate, endDate, target);
        Snackbar.make(cordinatorlayout, saveResponce, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        if (saveResponce.toString().equalsIgnoreCase("Data updated Successfully")) {
            Toast.makeText(getApplicationContext(), "Data updated Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
