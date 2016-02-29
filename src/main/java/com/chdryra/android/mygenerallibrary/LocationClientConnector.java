/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Handles connection to Google Play services for Places API lookup tasks.
 */
public class LocationClientConnector implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LocationClientConnector";
    private static final int MAX_CONNECTION_TRIES = 3;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private final GoogleApiClient mApiClient;
    private final Activity mActivity;
    private final Locatable mLocatable;
    private int mNumberConnectionTries = 0;

    /**
     * Callbacks for classes that want know when the Location Client has connected and found a
     * location.
     */
    public interface Locatable {
        void onLocated(Location location);

        void onLocationClientConnected(Location location);
    }

    public LocationClientConnector(Activity activity, Locatable locatable) {
        mActivity = activity;
        mLocatable = locatable;
        mApiClient = new GoogleApiClient.Builder(mActivity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public void connect() {
        if (!mApiClient.isConnected()) {
            mApiClient.connect();
        }
    }

    public void disconnect() {
        mApiClient.disconnect();
    }

    public boolean locate() {
        if (mApiClient.isConnected()) {
            Location location = getLastLocation();
            if (location != null) {
                mLocatable.onLocated(location);
                return true;
            }
        }

        return false;
    }

    private Location getLastLocation() {
        return LocationServices.FusedLocationApi.getLastLocation(mApiClient);
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
                    Log.e(TAG, "Tried contacting location services " + MAX_CONNECTION_TRIES + "" +
                            " times. Problems persist.", e);
                }
            }
        } else {
            Log.i(TAG, "Error code connection to location services: " + connectionResult
                    .getErrorCode());
        }
    }

    @Override
    public void onConnected(Bundle arg0) {
        Location location = getLastLocation();
        if (location != null) mLocatable.onLocationClientConnected(location);
        Log.i(TAG, "LocationClient connected");
    }

    @Override
    public void onConnectionSuspended(int index) {

    }
}
