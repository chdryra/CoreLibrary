package com.chdryra.android.mygenerallibrary;

import java.util.Iterator;
import java.util.LinkedList;

import android.view.View;
import android.widget.TextView;

import com.chdryra.android.mygenerallibrary.GridViewCellAdapter.GridViewable;

public class GVStrings implements GridViewable, Iterable<String> {
	private LinkedList<String> mData = new LinkedList<String>();
	
	public void add(String string) {
		if(string != null && string.length() > 0)
			mData.add(string);
	}
	
	public void remove(String string) {
		mData.remove(string);
	}
	
	@Override
	public int size() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
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
		public void updateView(Object data, int textColour) {
			mTextView.setText((String)data);
			mTextView.setTextColor(textColour);
		}
		
	}

	@Override
	public Iterator<String> iterator() {
		return mData.iterator();
	}
}
