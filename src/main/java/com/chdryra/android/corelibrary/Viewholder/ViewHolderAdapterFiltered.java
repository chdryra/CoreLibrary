/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Viewholder;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

/**
 * Created by: Rizwan Choudrey
 * On: 12/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class ViewHolderAdapterFiltered<T extends ViewHolderData> extends ViewHolderAdapter
        implements Filterable {

    private final VhQueryFilter<T> mFilter;
    private final T mNullItem;
    private ViewHolderDataList<T> mFiltered = new VhDataList<>();
    private ViewHolderDataList<T> mInitialList = new VhDataList<>();

    public ViewHolderAdapterFiltered(Context context,
                                     ViewHolderDataList<T> initialList,
                                     T nullItem,
                                     VhQueryFilter<T> filter) {
        super(context, initialList);
        mFilter = filter;
        mNullItem = nullItem;
        mInitialList = initialList;
        mFiltered = initialList;
        setData(mFiltered);
    }

    public void filter(CharSequence query) {
        getFilter().filter(query);
    }

    @Override
    public int getCount() {
        return mFiltered != null ? mFiltered.size() : 0;
    }

    @Override
    public T getItem(int index) {
        return (mFiltered != null && mFiltered.size() > 0 ? mFiltered.get(index) : mNullItem);
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
                mFiltered = (ViewHolderDataList<T>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
