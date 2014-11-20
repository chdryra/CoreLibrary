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

import java.util.ArrayList;

/**
 * An adapter that can be bound to an Autocomplete widget. Finds location names given a
 * LatLng. Consists of an address fetcher task to find addresses. Also implements a filter to
 * find autocomplete suggestions. Allows a default suggestion to be included via the constructor.
 *
 * @see com.chdryra.android.remoteapifetchers.FetcherPlacesAPI
 */
public class AutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
    private static final int    TEXT_VIEW_RESOURCE = android.R.layout.simple_list_item_1;

    private ArrayList<String>     mSuggestions;
    private ArrayList<String>     mDefaultSuggestions;
    private AutoCompleteSuggester mSuggester;

    public interface AutoCompleteSuggester {
        public ArrayList<String> getSuggestions(String query);
    }

    public AutoCompleteAdapter(Context context, ArrayList<String> initialSuggestions,
            AutoCompleteSuggester suggester) {
        super(context, TEXT_VIEW_RESOURCE);
        mDefaultSuggestions = initialSuggestions;
        mSuggestions = initialSuggestions;
        mSuggester = suggester;
    }

    @Override
    public int getCount() {
        return mSuggestions != null ? mSuggestions.size() : 0;
    }

    @Override
    public String getItem(int index) {
        return mSuggestions.get(index);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    //TODO need to put autocomplete fetching on a separate thread somehow.
                    mSuggestions = mSuggester.getSuggestions(constraint.toString());
                } else if (mDefaultSuggestions != null) {
                    mSuggestions = mDefaultSuggestions;
                }

                filterResults.values = mSuggestions;
                filterResults.count = mSuggestions.size();

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
}