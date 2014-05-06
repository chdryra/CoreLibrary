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

public class LocationClientConnector implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener{
	 
	private static final String TAG = "LocationClientConnector";
	public final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	private LocationClient mLocationClient;
	private Activity mActivity;
	private Locatable mLocatable;
	private LatLng mLatLng;
	
	public LocationClientConnector(Activity activity, Locatable locatable) {
		mActivity = activity;
		mLocatable = locatable;
		mLocationClient = new LocationClient(mActivity, this, this);
	}
	
	public void connect() {
		if(!mLocationClient.isConnected())
			mLocationClient.connect();
	}
	
	public void disconnect() {
		mLocationClient.disconnect();
	}
	
	public void locate() {
		Location location = mLocationClient.getLastLocation();
		if(location != null)
			mLocatable.setLocationLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		 if (connectionResult.hasResolution()) {
	            try {
	                connectionResult.startResolutionForResult( mActivity, CONNECTION_FAILURE_RESOLUTION_REQUEST);
	            } catch (IntentSender.SendIntentException e) {
	                e.printStackTrace();
	            }
	        } else {
	            Log.i(TAG, "Error code connection to location services: " + connectionResult.getErrorCode());
	        }		
	}

	@Override
	public void onConnected(Bundle arg0) {
		Log.i(TAG, "LocationClient connected");
		if(mLatLng == null) {
			Location location = mLocationClient.getLastLocation();
			if(location != null) {
				mLatLng = new LatLng(location.getLatitude(), location.getLongitude());
				mLocatable.setLocationLatLng(mLatLng);
			}
		}
	}

	@Override
	public void onDisconnected() {
		Log.i(TAG, "LocationClient disconnected");
	}
	
}
