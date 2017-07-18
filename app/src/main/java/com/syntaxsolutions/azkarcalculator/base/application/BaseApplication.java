package com.syntaxsolutions.azkarcalculator.base.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.syntaxsolutions.azkarcalculator.base.database.EventHeaderTable;
import com.syntaxsolutions.azkarcalculator.constants.Constants;

/**
 * Created by suhail.kk on 05-09-2016.
 */
public class BaseApplication extends Application {
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        Stetho.initializeWithDefaults(this);
        Fresco.initialize(this);
        context = this;


    }

    private void storeDefaultVlueToDb() {

        EventHeaderTable eventHeaderTable = new EventHeaderTable();
        eventHeaderTable.setEventId(1);
        eventHeaderTable.setEventName("zikr");
        eventHeaderTable.save();
        eventHeaderTable.setEventId(2);
        eventHeaderTable.setEventName("swalath");
        eventHeaderTable.save();
        eventHeaderTable.setEventId(3);
        eventHeaderTable.setEventName("Qathm");
        eventHeaderTable.save();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public StorageReference getFirebaseStorage(String subpath) {
        if (subpath != null && subpath.length() > 0) {
            firebaseStorage = FirebaseStorage.getInstance();
            storageReference = firebaseStorage.getReferenceFromUrl(Constants.FIREBASE_STORAGE_PATH + "/" + subpath);

        } else {
            storageReference = firebaseStorage.getReferenceFromUrl(Constants.FIREBASE_STORAGE_PATH);
        }
        return storageReference;
    }

    public StorageReference getFirebaseStorage() {
        storageReference = firebaseStorage.getReferenceFromUrl(Constants.FIREBASE_STORAGE_PATH);
        return storageReference;
    }

    public static Context getApplicationBase() {
        return context;
    }

}
