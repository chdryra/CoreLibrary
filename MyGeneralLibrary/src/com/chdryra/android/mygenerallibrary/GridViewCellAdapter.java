package com.chdryra.android.mygenerallibrary;


import java.util.Comparator;
import java.util.Iterator;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GridViewCellAdapter extends BaseAdapter {	
	private Activity mActivity;
	private GridViewable<?> mData;
	private int mCellView;
	private int mCellWidth;
	private int mCellHeight;
	
	public GridViewCellAdapter(Activity activity, GridViewable<?> data, int cellView, int cellWidth, int cellHeight){
	    mActivity = activity;
		mData = data;
		mCellView = cellView;
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
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public Object getItem(int position) {
		return mData.getItem(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		
		if (convertView == null) {						
			convertView = mActivity.getLayoutInflater().inflate(mCellView, parent, false);
			convertView.getLayoutParams().height = mCellHeight;
			convertView.getLayoutParams().width = mCellWidth;
			vh = mData.getViewHolder(convertView);
			convertView.setTag(vh);
		} else
			vh = (ViewHolder)convertView.getTag();
		
		vh.updateView(getItem(position));
		
		return(convertView);
	};		

	public interface GridViewable<T> extends Iterable<T>{
		public int size();
		public T getItem(int position);
		public ViewHolder getViewHolder(View convertView);
	}
};
