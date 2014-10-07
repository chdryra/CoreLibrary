/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.model.LatLng;

/**
 * Handles connection to Google Play services for Places API lookup tasks.
 */
public class LocationClientConnector implements GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {

    private static final String TAG                                   = "LocationClientConnector";
    private static final int    MAX_CONNECTION_TRIES                  = 3;
    private final static int    CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private final LocationClient mLocationClient;
    private final Activity       mActivity;
    private final Locatable      mLocatable;
    private int mNumberConnectionTries = 0;

    public LocationClientConnector(Activity activity, Locatable locatable) {
        mActivity = activity;
        mLocatable = locatable;
        mLocationClient = new LocationClient(mActivity, this, this);
    }

    public void connect() {
        if (!mLocationClient.isConnected()) {
            mLocationClient.connect();
        }
    }

    public void disconnect() {
        mLocationClient.disconnect();
    }

    public void locate() {
        if (mLocationClient.isConnected()) {
            return;
        }

        Location location = mLocationClient.getLastLocation();
        if (location != null) {
            mLocatable.onLocated(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(mActivity,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                if (++mNumberConnectionTries < MAX_CONNECTION_TRIES) {
                    Log.i(TAG, "Problems contacting location services. Trying again...");
                    mLocationClient.connect();
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
        Location location = mLocationClient.getLastLocation();
        if (location != null) {
            mLocatable.onLocationClientConnected(new LatLng(location.getLatitude(),
                    location.getLongitude()));
        }

        Log.i(TAG, "LocationClient connected");
    }

    @Override
    public void onDisconnected() {
        Log.i(TAG, "LocationClient disconnected");
    }

    /**
     * Callbacks for classes that want know when the Location Client has connected and found a
     * location.
     */
    public interface Locatable {
        public void onLocated(LatLng latLng);

        public void onLocationClientConnected(LatLng latLng);
    }

}
