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
	
	public LocationClientConnector(Activity activity, Locatable locatable) {
		mActivity = activity;
		mLocatable = locatable;
		mLocationClient = new LocationClient(mActivity, this, this);
	}
	
	public boolean isConnected() {
		return mLocationClient.isConnected();
	}
	
	public void connect() {
		if(!isConnected())
			mLocationClient.connect();
	}
	
	public void disconnect() {
		mLocationClient.disconnect();
	}
	
	public void locate() {
		if(!isConnected())
			return;
		
		Location location = mLocationClient.getLastLocation();
		if(location != null)
			mLocatable.onLocated(new LatLng(location.getLatitude(), location.getLongitude()));
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
		Location location = mLocationClient.getLastLocation();
		if(location != null) {
			mLocatable.onLocationClientConnected(new LatLng(location.getLatitude(), location.getLongitude()));
		}

		Log.i(TAG, "LocationClient connected");
	}

	@Override
	public void onDisconnected() {
		Log.i(TAG, "LocationClient disconnected");
	}

	public interface Locatable {
		public void onLocated(LatLng latLng);
		public void onLocationClientConnected(LatLng latLng);
	}

}
