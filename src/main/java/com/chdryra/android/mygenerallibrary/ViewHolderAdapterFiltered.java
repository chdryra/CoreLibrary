/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 12 March, 2015
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
    private ViewHolderDataList mFiltered    = new ViewHolderDataList<>();
    private ViewHolderDataList mInitialList = new ViewHolderDataList<>();

    public interface QueryFilter {
        public ViewHolderDataList filter(String query);
    }

    public ViewHolderAdapterFiltered(Context context, ViewHolderDataList initialList,
            QueryFilter filter) {
        super(context, initialList);
        if (initialList != null) {
            mInitialList = initialList;
            mFiltered = initialList;
        }
        mFilter = filter;
    }

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

                mFiltered = mInitialList;
                if (constraint != null && constraint.length() > 0) {
                    mFiltered = mFilter.filter(constraint.toString());
                }

                filterResults.values = mFiltered;
                filterResults.count = getCount();

                mInitialList = mFiltered;
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

    public void filter(CharSequence query) {
        getFilter().filter(query);
    }
}
