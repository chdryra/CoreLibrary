/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

/**
 * Created by: Rizwan Choudrey
 * On: 12/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class ViewHolderAdapterFiltered extends ViewHolderAdapter
        implements Filterable {

    private final QueryFilter mFilter;
    private ViewHolderDataList mFiltered = new VhDataList();
    private ViewHolderDataList mInitialList = new VhDataList();

    public interface QueryFilter {
        ViewHolderDataList filter(String query);
    }

    public ViewHolderAdapterFiltered(Context context, ViewHolderDataList initialList,
                                     QueryFilter filter) {
        super(context, initialList);
        if (initialList != null) {
            mInitialList = initialList;
            mFiltered = initialList;
        }
        mFilter = filter;
        setData(mFiltered);
    }

    public void filter(CharSequence query) {
        getFilter().filter(query);
    }

    //Overridden
    @Override
    public int getCount() {
        return mFiltered != null ? mFiltered.size() : 0;
    }

    @Override
    public ViewHolderData getItem(int index) {
        return (ViewHolderData) (mFiltered != null && mFiltered.size() > 0 ?
                mFiltered.getItem(index) : null);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                ViewHolderDataList filtered = mInitialList;
                if (constraint != null && constraint.length() > 0) {
                    filtered = mFilter.filter(constraint.toString());
                }

                filterResults.values = filtered;
                filterResults.count = filtered.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFiltered = (ViewHolderDataList) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
