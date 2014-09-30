/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public abstract class ViewHolderBasic implements ViewHolder {
    private final int        mLayout;
    private final VHInflater mInflater;
    protected     View       mInflated;

    protected ViewHolderBasic(int layout) {
        mLayout = layout;
        mInflater = getDefaultInflater();
    }

    protected VHInflater getDefaultInflater() {
        return new VHInflater() {
            @Override
            public View inflate(Activity activity, ViewGroup parent) {
                return activity.getLayoutInflater().inflate(mLayout, parent, false);
            }
        };
    }

    @Override
    public void inflate(Activity activity, ViewGroup parent) {
        mInflated = mInflater.inflate(activity, parent);
        mInflated.setTag(this);
        initViewsToUpdate();
    }

    @Override
    public abstract View updateView(GVData data);

    protected abstract void initViewsToUpdate();

    protected View getView(int viewID) {
        return mInflated != null ? mInflated.findViewById(viewID) : null;
    }

    public interface VHInflater {
        public View inflate(Activity activity, ViewGroup parent);
    }

}
