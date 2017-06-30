package com.syntaxsolutions.azkarcalculator.view.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.application.BaseActivity;
import com.syntaxsolutions.azkarcalculator.dto.InformationDetailsDto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Suhail k k on 28-04-2017.
 */

public class InformationFeedDetailsActivity extends BaseActivity {
    ViewStub vieStub;
    TextView informationFeedContent;
    int feedId;
    ProgressDialog progressDialog;
    ImageView imageView;
    String imageName;
    String title;
    TextView feedHead;
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        vieStub = (ViewStub) findViewById(R.id.contentLayout);
        vieStub.setLayoutResource(R.layout.information_feed_layout);
        vieStub.inflate();
        setToolBar();
        setFloatingButton();
        informationFeedContent = (TextView) findViewById(R.id.informationFeedContent);
        imageView = (ImageView) findViewById(R.id.imageView);
        feedHead = (TextView) findViewById(R.id.feedTitle);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        getIntentExtra();
        downloadImage();
        getContent();


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
        getSupportActionBar().setTitle("Information Feed");

    }

    private void getContent() {
        progressDialog = new ProgressDialog(InformationFeedDetailsActivity.this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("InformationFeedDetails");
        Query queryRef = mDatabase.orderByChild("feedId").equalTo(feedId);
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                InformationDetailsDto informationDetailsDto = new InformationDetailsDto();
                if (snapshot != null && snapshot.getValue() != null) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        informationDetailsDto = postSnapshot.getValue(InformationDetailsDto.class);
                    }
                }
                progressDialog.dismiss();
                informationFeedContent.setText(informationDetailsDto.getData());
                feedHead.setText(title);

            }


            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

    private void getIntentExtra() {

        Bundle extras = getIntent().getExtras();
        imageName = extras.getString("imageUrl");
        feedId = extras.getInt("feedId", -1);
        title = extras.getString("title");


    }

    public void downloadImage() {
        StorageReference storageReference;
        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(imageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                progressBar.setVisibility(View.GONE);
                Picasso.with(InformationFeedDetailsActivity.this).load(uri.toString()).fit().centerCrop().into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
    }
}