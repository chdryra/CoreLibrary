/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 19 November, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import com.chdryra.android.remoteapifetchers.FetcherPlacesAPI;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by: Rizwan Choudrey
 * On: 19/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class PlaceSuggester {
    private final Context               mContext;
    private final LatLng                mLatLng;
    private final FetchCompleteListener mListener;

    public interface FetchCompleteListener {
        public void onAddressesFound(ArrayList<String> addresses);
    }

    public PlaceSuggester(Context context, LatLng latlng, FetchCompleteListener listener) {
        mContext = context;
        mLatLng = latlng;
        mListener = listener;
    }

    public void fetch(int number) {
        new AddressFinderTask().execute(number);
    }

    private String formatAddress(Address address) {
        return String.format(
                "%s%s",
                // If there's a street address, add it
                address.getMaxAddressLineIndex() > 0 ?
                        address.getAddressLine(0) : "",
                // Locality is usually a city
                address.getLocality() != null ?
                        ", " + address.getLocality() : "");
    }

    /**
     * Finds nearest addresses given a LatLng on a separate thread using FetcherPlacesAPI. If this
     * returns nothing, tries to use Android's built in Geocoder class.
     */
    private class AddressFinderTask extends AsyncTask<Integer, Void, ArrayList<String>> {
        private final static String TAG = "AddressFinderTask";

        @Override
        protected ArrayList<String> doInBackground(Integer... params) {
            Integer numberToGet = params[0];
            ArrayList<String> namesFromGoogle = new ArrayList<String>();
            if (mLatLng == null || numberToGet == 0) return namesFromGoogle;

            namesFromGoogle = FetcherPlacesAPI.fetchNearestNames(mLatLng, numberToGet);

            if (namesFromGoogle.size() > 0) {
                return namesFromGoogle;
            } else {
                //Try using Geocoder
                Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(mLatLng.latitude, mLatLng.longitude,
                            numberToGet);
                } catch (IOException e1) {
                    Log.e(TAG, "IOException: trying to get address using latitude " + mLatLng
                            .latitude + ", longitude " + mLatLng.longitude, e1);
                    Log.i(TAG, "Address is null");
                } catch (IllegalArgumentException e2) {
                    Log.e(TAG, "IllegalArgumentException: trying to get address using latitude " +
                            mLatLng.latitude + ", longitude " + mLatLng.longitude, e2);
                    Log.i(TAG, "Address is null");
                }

                if (addresses != null && addresses.size() > 0) {
                    ArrayList<String> addressesList = new ArrayList<String>();
                    for (int i = 0; i < addressesList.size(); ++i) {
                        addressesList.add(formatAddress(addresses.get(i)));
                    }

                    return addressesList;
                } else {
                    return null;
                }
            }
        }

        @Override
        protected void onPostExecute(ArrayList<String> addresses) {
            super.onPostExecute(addresses);
            mListener.onAddressesFound(addresses);
        }
    }
}
