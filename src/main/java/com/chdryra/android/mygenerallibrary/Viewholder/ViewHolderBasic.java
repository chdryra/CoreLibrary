/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Primary implementation of the {@link ViewHolder}
 * interface for the ViewHolder pattern used in {@link ViewHolderAdapter}.
 * <p/>
 * <p>
 * Need to implement {@link #updateView(ViewHolderData)} to update the list of updateable views
 * (referenced by viewIds passed to the constructor) when new
 * {@link ViewHolderData} is presented.
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

    protected final <T extends View> T getView(int viewId, Class<T> tClass) {
        return (T)getView(viewId);
    }

    protected final View getView(int viewId) {
        return mUpdateableViews.get(viewId);
    }

    //Helper method as used most often...
    protected final void setText(int viewId, @Nullable String text) {
        TextView view = getView(viewId, TextView.class);
        if(view != null) view.setText(text);
    }

    protected final void setImage(int viewId, @Nullable Bitmap image) {
        ImageView view = getView(viewId, ImageView.class);
        if(view != null) view.setImageBitmap(image);
    }

    //Overridden
    @Override
    public void inflate(Context context, ViewGroup parent) {
        if(mInflated == null) {
            mInflated = LayoutInflater.from(context).inflate(mLayout, parent, false);
            if (mInflated != null) {
                for (int viewId : mUpdateableViewIds) {
                    mUpdateableViews.put(viewId, mInflated.findViewById(viewId));
                }
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
