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
import android.widget.BaseAdapter;

/**
 * Allows a collection of items to be viewed as a sorted collection of views with a specified view
 * width and height.
 * <p/>
 * <p>
 * Uses the ViewHolder pattern in Android for views that use components from a limited
 * reusable pool. Item views are only searched for and inflated once then stored in the
 * tags of the reuseable objects. The data within these is overwritten with whatever is
 * required for their reuse. Aids memory and speed performance.
 * </p>
 *
 * @see <a href="http://developer.android.com/training/improving-layouts/smooth-scrolling
 * .html#ViewHolder">ViewHolder pattern</a>
 * @see com.chdryra.android.mygenerallibrary.ViewHolder
 */
public class ViewHolderAdapter extends BaseAdapter {
    private final Activity                               mActivity;
    private final int mViewWidth;
    private final int mViewHeight;
    private       SortableList<? extends ViewHolderData> mData;

    public ViewHolderAdapter(Activity activity, SortableList<? extends ViewHolderData> data,
            int viewWidth, int viewHeight) {
        mActivity = activity;
        mData = data;
        mViewWidth = viewWidth;
        mViewHeight = viewHeight;
    }

    public void setData(SortableList<? extends ViewHolderData> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public ViewHolderData getItem(int position) {
        mData.sort();
        return mData.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderData data = getItem(position);
        if (!data.isValidForDisplay()) return null;

        ViewHolder vh;
        if (convertView == null) {
            vh = data.getViewHolder();
            vh.inflate(mActivity, parent);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.updateView(data);
        convertView = vh.getView();
        convertView.setTag(vh);
        convertView.getLayoutParams().height = mViewHeight;
        convertView.getLayoutParams().width = mViewWidth;

        return convertView;
    }
}
