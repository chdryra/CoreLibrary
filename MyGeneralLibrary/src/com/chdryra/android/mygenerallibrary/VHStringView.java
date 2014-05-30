package com.chdryra.android.mygenerallibrary;

import android.view.View;
import android.widget.TextView;

public class VHStringView implements ViewHolder {
	public static final int LAYOUT = R.layout.grid_cell_string_view;
	
	private TextView mTextView;
	
	public VHStringView(View convertView) {
		init(convertView);
	}
	
	private void init(View view) {
		mTextView = (TextView)view.findViewById(R.id.text_view);
	}
	
	@Override
	public void updateView(Object data) {
		mTextView.setText(((GVString)data).toString());
	}
}