/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.chdryra.android.remoteapifetchers.FetcherPlacesAPI;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * An adapter that can be bound to an Autocomplete widget. Finds location names given a
 * LatLng. Consists of an address fetcher task to find addresses. Also implements a filter to
 * find autocomplete suggestions. Allows a default suggestion to be included via the constructor.
 *
 * @see com.chdryra.android.remoteapifetchers.FetcherPlacesAPI
 */
public class LocationNameAdapter extends ArrayAdapter<String> implements Filterable {
    private static final String TAG                = "LocationNameAdapter";
    private static final String SEARCHING          = "searching nearby...";
    private static final String NO_LOCATION        = "location not found...";
    private static final int    TEXT_VIEW_RESOURCE = android.R.layout.simple_list_item_1;

    private final LatLng            mLatLng;
    private       ArrayList<String> mLocationSuggestions;
    private       ArrayList<String> mLocationDefaultSuggestions;
    private       String            mPrimaryDefaultSuggestion;

    public LocationNameAdapter(Context context, LatLng latlng, int numberSuggestions,
            String primaryDefaultSuggestion) {
        super(context, TEXT_VIEW_RESOURCE);
        mLatLng = latlng;
        if (numberSuggestions > 0) {
            if (primaryDefaultSuggestion != null && primaryDefaultSuggestion.length() > 0) {
                mPrimaryDefaultSuggestion = primaryDefaultSuggestion;
                numberSuggestions--;
            }

            mLocationSuggestions = new ArrayList<String>();
            if (mLatLng != null) {
                mLocationSuggestions.add(SEARCHING);
                GetAddressTask task = new GetAddressTask(context, mLatLng);
                task.execute(numberSuggestions);
            } else {
                mLocationSuggestions.add(NO_LOCATION);
            }

            notifyDataSetChanged();
        }
    }

    /**
     * Finds nearest names given a LatLng on a separate thread using FetcherPlacesAPI. If this
     * returns nothing, tries to use Android's built in Geocoder class.
     */
    private class GetAddressTask extends AsyncTask<Integer, Void, ArrayList<String>> {

        final Context mContext;
        final LatLng  mLatLng;

        public GetAddressTask(Context context, LatLng latlng) {
            super();
            mContext = context;
            mLatLng = latlng;
        }

        @Override
        protected ArrayList<String> doInBackground(Integer... params) {
            Integer numberToGet = params[0];

            ArrayList<String> namesFromGoogle = FetcherPlacesAPI.fetchNearestNames(mLatLng,
                    numberToGet);

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
            if (addresses != null) {
                mLocationDefaultSuggestions = addresses;
                if (mPrimaryDefaultSuggestion != null) {
                    mLocationDefaultSuggestions.add(0, mPrimaryDefaultSuggestion);
                }
                mLocationSuggestions = new ArrayList<String>(mLocationDefaultSuggestions);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getCount() {
        if (mLocationSuggestions != null) {
            return mLocationSuggestions.size();
        } else {
            return 0;
        }
    }

    @Override
    public String getItem(int index) {
        return mLocationSuggestions.get(index);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    //TODO need to put autocomplete fetching on a separate thread somehow.
                    ArrayList<String> suggestions = FetcherPlacesAPI.fetchAutoCompleteSuggestions
                            (constraint.toString(), mLatLng);

                    ArrayList<String> shortened = new ArrayList<String>();
                    for (String suggestion : suggestions) {
                        shortened.add(formatAddress(suggestion));
                    }

                    mLocationSuggestions = shortened;
                } else if (mLocationDefaultSuggestions != null) {
                    mLocationSuggestions = mLocationDefaultSuggestions;
                }

                filterResults.values = mLocationSuggestions;
                filterResults.count = mLocationSuggestions.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    public void findSuggestions(String query) {
        getFilter().filter(query);
    }

    private String formatAddress(String address) {
        String[] addressComponents = address.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append(addressComponents[0]);
        if (addressComponents.length > 1) {
            sb.append(",");
            sb.append(addressComponents[1]);
        }

        return sb.toString();
    }

    public void findSuggestions(CharSequence query) {
        getFilter().filter(query);
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
}