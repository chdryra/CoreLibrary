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

public class GridViewCellAdapter extends BaseAdapter {	
	private Activity mActivity;
	private GridViewable<?> mData;
	private int mCellWidth;
	private int mCellHeight;
	private boolean mSortedGrid = true;
	
	public GridViewCellAdapter(Activity activity, GridViewable<? extends GVData> data, int cellWidth, int cellHeight){
	    mActivity = activity;
		mData = data;
		mCellWidth = cellWidth;
		mCellHeight = cellHeight;
	}
	
	public void setData(GridViewable<?> data) {
		mData = data;
		notifyDataSetChanged();
	}
	
	public void setSortedGrid(boolean sortedGrid) {
		mSortedGrid = sortedGrid;
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public Object getItem(int position) {
		if(mSortedGrid)
			mData.sort();
		
		return mData.getItem(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		
		if (convertView == null) {						
			vh = mData.getViewHolder(position);
			vh.inflate(mActivity, parent);
		} else
			vh = (ViewHolder)convertView.getTag();
		
		convertView = vh.updateView((GVData)getItem(position));
		convertView.setTag(vh);
		convertView.getLayoutParams().height = mCellHeight;
		convertView.getLayoutParams().width = mCellWidth;
		
		return convertView;
	};		

	public interface GridViewable<T> extends Iterable<T>{
		public int size();
		public T getItem(int position);
		public ViewHolder getViewHolder(int position);
		public void sort();
	}
};
