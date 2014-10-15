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
 * Allows a collection of items that follow the GridViewable interface to be viewed as a sorted
 * collection of grid cells in a GridView with a specified cell width and cell height.
 * <p/>
 * <p>
 * Uses the ViewHolder pattern in Android for views that use components from a limited
 * reusable pool. Grid cell views are only searched for and inflated once then stored in the
 * tags of the reuseable objects. The data within these is overwritten with whatever is
 * required for their reuse. Aids memory and speed performance.
 * </p>
 *
 * @see <a href="http://developer.android.com/training/improving-layouts/smooth-scrolling
 * .html#ViewHolder">ViewHolder pattern</a>
 * @see com.chdryra.android.mygenerallibrary.ViewHolder
 * @see com.chdryra.android.mygenerallibrary.GVData
 */
public class GridViewCellAdapter extends BaseAdapter {
    private final Activity        mActivity;
    private final int             mCellWidth;
    private final int             mCellHeight;
    private       GridViewable<?> mData;

    public GridViewCellAdapter(Activity activity, GridViewable<?> data, int cellWidth,
                               int cellHeight) {
        mActivity = activity;
        mData = data;
        mCellWidth = cellWidth;
        mCellHeight = cellHeight;
    }

    public void setData(GridViewable<?> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        mData.sort();
        return mData.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GVData data = (GVData) getItem(position);
        if (!data.isValidForDisplay()) return null;

        ViewHolder vh;
        if (convertView == null) {
            vh = mData.getViewHolder(position);
            vh.inflate(mActivity, parent);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.updateView(data);
        convertView = vh.getView();
        convertView.setTag(vh);
        convertView.getLayoutParams().height = mCellHeight;
        convertView.getLayoutParams().width = mCellWidth;

        return convertView;
    }

    /**
     * Interface that a collection of items must follow if the items are to be viewable in a
     * GridView using a GridViewCellAdapter. Utilises the ViewHolder android pattern.
     *
     * @param <T>: the item type in the collection.
     * @see com.chdryra.android.mygenerallibrary.ViewHolder
     */
    public interface GridViewable<T> extends Iterable<T> {
        public int size();

        public T getItem(int position);

        public ViewHolder getViewHolder(int position);

        public void sort();
    }
}
