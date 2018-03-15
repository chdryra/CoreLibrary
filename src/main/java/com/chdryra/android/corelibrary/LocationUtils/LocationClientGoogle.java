/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.LocationUtils;

import android.app.Activity;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.chdryra.android.corelibrary.AsyncUtils.CallbackMessage;
import com.chdryra.android.corelibrary.OtherUtils.RequestCodeGenerator;
import com.chdryra.android.corelibrary.OtherUtils.TagKeyGenerator;
import com.chdryra.android.corelibrary.Permissions.PermissionResult;
import com.chdryra.android.corelibrary.Permissions.PermissionsManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

/**
 * Handles connection to Google Play services for Places API lookup tasks.
 */
public class LocationClientGoogle implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationClient, PermissionsManager.PermissionsCallback {

    private final static String TAG = TagKeyGenerator.getTag(LocationClientGoogle.class);
    private final static int PERMISSIONS = RequestCodeGenerator.getCode
            (LocationClientGoogle.class);

    private final static int MAX_CONNECTION_TRIES = 3;
    private final static String PROBLEM_CONNECTING = "Tried contacting location services " +
            MAX_CONNECTION_TRIES + " times. Problems persist.";
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private final static CallbackMessage NULL_LOCATION = CallbackMessage.error("Null location");
    private final static CallbackMessage NOT_CONNECTED = CallbackMessage.error("Not connected");
    private final static CallbackMessage OK = CallbackMessage.ok();
    private static final PermissionsManager.Permission LOCATION = PermissionsManager.Permission.LOCATION;

    private final GoogleApiClient mApiClient;
    private final Activity mActivity;
    private final PermissionsManager mPermissions;

    private Locatable mLocatable;
    private int mNumberConnectionTries = 0;

    public LocationClientGoogle(Activity activity, PermissionsManager permissions) {
        mActivity = activity;
        mPermissions = permissions;
        mApiClient = new GoogleApiClient.Builder(mActivity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onPermissionsResult(int requestCode, List<PermissionResult> results) {
        if(requestCode == PERMISSIONS && results.get(0).isGranted(LOCATION)) locate();
    }

    @Override
    public void connect(Locatable locatable) {
        mLocatable = locatable;
        if (!mApiClient.isConnected()) mApiClient.connect();
    }

    @Override
    public void disconnect() {
        mApiClient.disconnect();
    }

    @Override
    public void locate() {
        if(mLocatable == null) return;
        if (mApiClient.isConnected()) {
            if(!mPermissions.hasPermissions(LOCATION)) {
                requestPermission();
            } else {
                getLastLocation();
            }
        } else {
            mLocatable.onLocated(null, NOT_CONNECTED);
        }
    }

    private void requestPermission() {
        mPermissions.requestPermissions(PERMISSIONS, this, PermissionsManager.Permission.LOCATION);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(mActivity,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                if (++mNumberConnectionTries < MAX_CONNECTION_TRIES) {
                    Log.i(TAG, "Problems contacting location services. Trying again...");
                    mApiClient.connect();
                } else {
                    Log.e(TAG, PROBLEM_CONNECTING, e);
                    mLocatable.onConnected(null, CallbackMessage.error(PROBLEM_CONNECTING));
                }
            }
        } else {
            String msg = "Error code connection to location services: " + connectionResult
                    .getErrorCode();
            Log.i(TAG, msg);
            mLocatable.onConnected(null, CallbackMessage.error(msg));
        }
    }

    @Override
    public void onConnected(Bundle arg0) {
        locate();
    }

    @Override
    public void onConnectionSuspended(int index) {

    }

    private void getLastLocation() {
        try {
            LocationServices.getFusedLocationProviderClient(mActivity)
                    .getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    mLocatable.onLocated(location, location == null ? NULL_LOCATION : OK);
                }
            });
        } catch (SecurityException e) {
            requestPermission();
        }
    }
}
