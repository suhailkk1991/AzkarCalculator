package com.syntaxsolutions.azkarcalculator.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.application.BaseActivity;
import com.syntaxsolutions.azkarcalculator.base.application.BaseApplication;
import com.syntaxsolutions.azkarcalculator.dto.InformationFeed;
import com.syntaxsolutions.azkarcalculator.util.NetworkStateReciver;
import com.syntaxsolutions.azkarcalculator.util.NetworkUtil;
import com.syntaxsolutions.azkarcalculator.view.adapter.HomeNewsFeedAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 26-12-2016.
 */

public class HomeActivity extends BaseActivity {
    ViewStub vieStub;
    TextView textView;
    List<InformationFeed> lstFeeds;
    StorageReference storageReference;
    RecyclerView recyclerView;
    HomeNewsFeedAdapter lstNewsFeedAdapter;
    ProgressDialog progressDialog;
    NetworkStateReciver networkStateReciver;
    int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        vieStub = (ViewStub) findViewById(R.id.contentLayout);
        vieStub.setLayoutResource(R.layout.azakar_home_layout);
        vieStub.inflate();
        setToolBar();
        setFloatingButton();
        recyclerView = (RecyclerView) findViewById(R.id.lstNewsFeeds);
        getTheDataFromFireBase();
    }

    @Override
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
        getSupportActionBar().setTitle("Zikr Home");

    }

    private void getTheDataFromFireBase() {
        if (NetworkUtil.isConnected()) {
            if (count == 0) {
                count++;
                registerNetworkReciver();
            }
            progressDialog = new ProgressDialog(HomeActivity.this);
            progressDialog.setMessage("Loading data...");
            progressDialog.show();
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("InformationFeedHeader");

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    InformationFeed feed = new InformationFeed();
                    lstFeeds = new ArrayList<InformationFeed>();
                    if (snapshot != null && snapshot.getValue() != null) {

                        for (DataSnapshot postSnapshot1 : snapshot.getChildren()) {

                            for (DataSnapshot postSnapshot2 : postSnapshot1.getChildren()) {
                                InformationFeed messageMap = postSnapshot2.getValue(InformationFeed.class);
                                lstFeeds.add(messageMap);
                            }

                        }
                        progressDialog.dismiss();
                        unRegisterNetworkReciver();
                        setAdapter();
                    }
                }

                @Override
                public void onCancelled(DatabaseError firebaseError) {

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "You are offline", Toast.LENGTH_LONG).show();
        }
    }

    private void setAdapter() {
        RecyclerView.LayoutManager mLayoutManager;
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        HomeNewsFeedAdapter homeNewsFeedAdapter = new HomeNewsFeedAdapter(lstFeeds, recyclerView, HomeActivity.this);
        recyclerView.setAdapter(homeNewsFeedAdapter);
    }

    public void downloadImage(String imageName, final ImageView imageView, final ProgressBar progressBar) {
        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(imageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(HomeActivity.this).load(uri.toString()).fit().centerCrop().into(imageView);
                imageView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

    }

    public void onClickCalled(int position) {

        Bundle extras = new Bundle();
        extras.putString("imageUrl", lstFeeds.get(position).getImageName());
        extras.putInt("feedId", lstFeeds.get(position).getFeedId());
        extras.putString("title", lstFeeds.get(position).getHead());
        Intent intent = new Intent(getApplicationContext(), InformationFeedDetailsActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public synchronized void registerNetworkReciver() {
        networkStateReciver = new NetworkStateReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReciver, intentFilter);

    }

    public synchronized void unRegisterNetworkReciver() {
        if (count > 0) {
            unregisterReceiver(networkStateReciver);
            count--;
        }
    }

    public void dismissProgressBar() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unRegisterNetworkReciver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterNetworkReciver();
    }
}
