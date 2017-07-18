package com.syntaxsolutions.azkarcalculator.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.syntaxsolutions.azkarcalculator.base.application.BaseApplication;
import com.syntaxsolutions.azkarcalculator.view.activity.HomeActivity;

/**
 * Created by Suhail k k on 18-07-2017.
 */

public class NetworkStateReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!NetworkUtil.isConnected()) {
            Toast.makeText(BaseApplication.getApplicationBase(), NetworkUtil.getConnectivityStatusString(context), Toast.LENGTH_LONG).show();
        }
        ((HomeActivity) context).dismissProgressBar();
    }
}
