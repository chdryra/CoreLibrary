/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Viewholder;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Primary implementation of the {@link ViewHolder}
 * interface for the ViewHolder pattern used in {@link ViewHolderAdapter}.
 * <p/>
 * <p>
 * Need to implement {@link #updateView(ViewHolderData)} to update the list of updateable views
 * (referenced by viewIds passed to the constructor) when new
 * {@link ViewHolderData} is presented.
 * Use {@link  #getView(int)} to retrieve these views once the layout is inflated.
 * </p>
 */
public abstract class ViewHolderBasic implements ViewHolder {
    private final int mLayout;
    private final int[] mUpdateableViewIds;
    private final SparseArray<View> mUpdateableViews;
    private View mInflated;

    protected ViewHolderBasic(int layoutId, int[] viewIds) {
        mLayout = layoutId;
        mUpdateableViewIds = viewIds;
        mUpdateableViews = new SparseArray<>(mUpdateableViewIds.length);
    }

    protected final View getView(int viewId) {
        return mUpdateableViews.get(viewId);
    }

    //Overridden
    @Override
    public void inflate(Context context, ViewGroup parent) {
        mInflated = LayoutInflater.from(context).inflate(mLayout, parent, false);
        if (mInflated != null) {
            for (int viewId : mUpdateableViewIds) {
                mUpdateableViews.put(viewId, mInflated.findViewById(viewId));
            }
        }
    }

    @Override
    public abstract void updateView(ViewHolderData data);

    @Override
    public View getView() {
        return mInflated;
    }
}