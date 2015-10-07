/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;


import android.content.Context;
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
    private final Context mContext;
    private final int mViewWidth;
    private final int mViewHeight;
    private ViewHolderDataList mData;
    private boolean mSetDimensions = false;
    private boolean mUniqueViews = false;

    //Constructors
    public ViewHolderAdapter(Context context, ViewHolderDataList data) {
        this(context, data, -1, -1, false);
    }

    public ViewHolderAdapter(Context context, ViewHolderDataList data, int viewWidth,
                             int viewHeight) {
        this(context, data, viewWidth, viewHeight, false);
    }

    public ViewHolderAdapter(Context context, ViewHolderDataList data, int viewWidth,
                             int viewHeight, boolean uniqueViews) {
        mContext = context;
        mData = data;
        mViewWidth = viewWidth;
        mViewHeight = viewHeight;
        mSetDimensions = true;
        mUniqueViews = uniqueViews;
    }

    public void setData(ViewHolderDataList data) {
        mData = data;
        notifyDataSetChanged();
    }

    //Overridden
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public ViewHolderData getItem(int position) {
        mData.sort();
        return (ViewHolderData) mData.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderData data = getItem(position);
        if (data == null || !data.isValidForDisplay()) return new View(mContext);

        ViewHolder vh;
        if (convertView == null || mUniqueViews) {
            vh = data.getViewHolder();
            vh.inflate(mContext, parent);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.updateView(data);
        convertView = vh.getView();
        convertView.setTag(vh);

        if (mSetDimensions) {
            convertView.getLayoutParams().height = mViewHeight;
            convertView.getLayoutParams().width = mViewWidth;
        }

        return convertView;
    }
}
