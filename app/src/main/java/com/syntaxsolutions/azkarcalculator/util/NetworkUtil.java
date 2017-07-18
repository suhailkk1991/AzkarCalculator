package com.syntaxsolutions.azkarcalculator.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.syntaxsolutions.azkarcalculator.base.application.BaseApplication;

/**
 * Created by Suhail k k on 18-07-2017.
 */

public class NetworkUtil {
    /**
     * Connectivity mnager object.
     */
    static ConnectivityManager connectivityManager;
    /**
     * Network info object.
     */
    static NetworkInfo wifiInfo;
    /**
     * Network info object.
     */
    static NetworkInfo mobileInfo;

    public static Context context;
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public static boolean isConnected() {

        try {
            context = BaseApplication.getApplicationBase();
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = false;
            if (activeNetwork != null) {
                isConnected = activeNetwork.isConnectedOrConnecting();
            }
            return isConnected;
        } catch (Exception e) {
            Log.d("Check net connectivity", "Exception" + e);
            return false;
        }

    }
    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == NetworkUtil.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == NetworkUtil.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }


}
