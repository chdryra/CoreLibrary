package com.chdryra.android.mygenerallibrary;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import android.view.View;
import android.widget.TextView;

import com.chdryra.android.mygenerallibrary.GridViewCellAdapter.GridViewable;

public class GVStrings implements GridViewable<String> {
	private LinkedList<String> mData = new LinkedList<String>();
	private boolean mIsSorted = false;
	
	public GVStrings() {	
	}
	
	public GVStrings(GVStrings strings) {
		for(String string : strings)
			add(string);
	}
	
	public void add(String string) {
		if(string != null && string.length() > 0)
			mData.add(string);
		mIsSorted = false;
	}
	
	public boolean contains(String string) {
		return mData.contains(string);
	}
	
	public void remove(String string) {
		mData.remove(string);
		mIsSorted = false;
	}

	public void removeAll() {
		mData.clear();
	}

	@Override
	public int size() {
		return mData.size();
	}

	@Override
	public String getItem(int position) {
		return mData.get(position);
	}
	
	@Override
	public ViewHolder getViewHolder(View convertView) {
		TextView textView = (TextView)convertView.findViewById(R.id.text_view);
		return new VHTextView(textView);
	}

	class VHTextView implements ViewHolder {
		private TextView mTextView;
		
		public VHTextView(TextView textView) {
			mTextView = textView;
		}
		
		@Override
		public void updateView(Object data) {
			mTextView.setText((String)data);
		}
		
	}

	@Override
	public Iterator<String> iterator() {
		return mData.iterator();
	}

	@Override
	public boolean isSorted() {
		return mIsSorted;
	}

	@Override
	public void sort() {
		if(!isSorted())
			Collections.sort(mData);
		mIsSorted = true;
	}

	@Override
	public void sort(Comparator<String> comparator) {
		if(!isSorted())
			Collections.sort(mData, comparator);
		mIsSorted = true;
	}
}
