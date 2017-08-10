/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.LocationServices.GooglePlacesApi;


import android.os.Bundle;
import android.support.annotation.NonNull;

import com.chdryra.android.mygenerallibrary.OtherUtils.RequestCodeGenerator;
import com.chdryra.android.mygenerallibrary.Permissions.PermissionResult;
import com.chdryra.android.mygenerallibrary.Permissions.PermissionsManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 11/03/2016
 * Email: rizwan.choudrey@gmail.com
 */
public abstract class GoogleLocationServiceBasic implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, PermissionsManager.PermissionsCallback {
    private static final int PERMISSION_REQUEST = RequestCodeGenerator.getCode(GoogleLocationServiceBasic.class);
    private static final PermissionsManager.Permission LOCATION = PermissionsManager.Permission
            .LOCATION;

    private final GoogleApiClient mClient;
    private final PermissionsManager mPermissions;

    protected abstract void doRequestOnConnected();

    protected abstract void onNotPermissioned();

    protected abstract void onNotConnected();

    public GoogleLocationServiceBasic(GoogleApiClient client, PermissionsManager permissions) {
        mClient = client;
        mPermissions = permissions;
        mClient.registerConnectionCallbacks(this);
        mClient.registerConnectionFailedListener(this);
    }

    public GoogleApiClient getClient() {
        return mClient;
    }

    void connectAndDoRequest() {
        if (!mClient.isConnected()) {
            mClient.connect();
        } else {
            doRequestOnConnected();
        }
    }

    public void disconnect() {
        mClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (!mPermissions.hasPermissions(LOCATION)) {
            requestPermissions();
        } else {
            doRequestOnConnected();
        }
    }

    void requestPermissions() {
        mPermissions.requestPermissions(PERMISSION_REQUEST, this, LOCATION);
    }

    @Override
    public void onPermissionsResult(int requestCode, List<PermissionResult> results) {
        if(requestCode == PERMISSION_REQUEST
                && results.size() == 1
                && results.get(0).isGranted(LOCATION)) {
            doRequestOnConnected();
        } else {
            onNotPermissioned();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        onNotConnected();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        onNotConnected();
    }
}
