package com.cmoatoto.maptracker.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by cmoatoto on 30/12/16.
 */

public class PermissionManager {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 30;

    /**
     * @param activity
     * @param permission the Permission from Manifest.permission package
     * @return if the permission has already been granted or not. If it return false,
     * an asynchronous system will ask for it
     */
    public boolean checkForPermission(FragmentActivity activity, String permission) {

        if (ContextCompat.checkSelfPermission(activity,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    permission)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                insisteForPermission(activity, permission);

                return false;

            } else {

                // No explanation needed, we can request the permission.
                requestPermission(activity, permission);

                return false;
            }
        } else {
            return true;
        }
    }

    private void requestPermission(FragmentActivity activity, String permission) {
        ActivityCompat.requestPermissions(activity,
                new String[]{permission},
                MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
        // app-defined int constant. The callback method gets the
        // result of the request.
    }

    public void insisteForPermission(final FragmentActivity activity, final String permission) {

        new AlertDialog.Builder(activity)
                .setCancelable(false)
                .setTitle("Access your location")
                .setMessage("I'm sorry mate, but we need your location if you want to record your journeys")
                .setPositiveButton("Add permission", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission(activity, permission);
                    }
                })
                .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .show();
    }
}
