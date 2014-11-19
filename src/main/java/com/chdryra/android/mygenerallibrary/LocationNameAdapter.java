/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.chdryra.android.remoteapifetchers.FetcherPlacesAPI;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * An adapter that can be bound to an Autocomplete widget. Finds location names given a
 * LatLng. Consists of an address fetcher task to find addresses. Also implements a filter to
 * find autocomplete suggestions. Allows a default suggestion to be included via the constructor.
 *
 * @see com.chdryra.android.remoteapifetchers.FetcherPlacesAPI
 */
public class LocationNameAdapter extends ArrayAdapter<String> implements Filterable,
        AddressSuggestionsFetcher.FetchCompleteListener {
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
                AddressSuggestionsFetcher fetcher = new AddressSuggestionsFetcher(context,
                        mLatLng, this);
                fetcher.fetch(numberSuggestions);
            } else {
                mLocationSuggestions.add(NO_LOCATION);
            }

            notifyDataSetChanged();
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

    public void findSuggestions(CharSequence query) {
        getFilter().filter(query);
    }

    @Override
    public void onAddressesFound(ArrayList<String> addresses) {
        mLocationDefaultSuggestions = addresses;
        if (mPrimaryDefaultSuggestion != null) {
            mLocationDefaultSuggestions.add(0, mPrimaryDefaultSuggestion);
        }
        mLocationSuggestions = new ArrayList<String>(mLocationDefaultSuggestions);
        notifyDataSetChanged();
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
}