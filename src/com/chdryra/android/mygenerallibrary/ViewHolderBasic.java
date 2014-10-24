/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Primary implementation of the {@link com.chdryra.android.mygenerallibrary.ViewHolder}
 * interface for the ViewHolder pattern used in GridViewCellAdapter.
 * <p/>
 * <p>
 * Need to implement <code>updateView(.)</code> to update the list of updateable views
 * (referenced by viewIds passed to the constructor) when new
 * {@link com.chdryra.android.mygenerallibrary.ViewHolderData} is presented.
 * Use <code>getView(int viewId)</code> to retrieve these views once the layout is inflated.
 * </p>
 */
public abstract class ViewHolderBasic implements ViewHolder {
    private final int               mLayout;
    private final int[]             mUpdateableViewIds;
    private final SparseArray<View> mUpdateableViews;
    protected     View              mInflated;

    protected ViewHolderBasic(int layoutId, int[] viewIds) {
        mLayout = layoutId;
        mUpdateableViewIds = viewIds;
        mUpdateableViews = new SparseArray<View>(mUpdateableViewIds.length);
    }

    @Override
    public void inflate(Activity activity, ViewGroup parent) {
        mInflated = activity.getLayoutInflater().inflate(mLayout, parent, false);
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

    protected final View getView(int viewId) {
        return mUpdateableViews.get(viewId);
    }
}
