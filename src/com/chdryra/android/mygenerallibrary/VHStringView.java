package com.chdryra.android.mygenerallibrary;

import android.view.View;
import android.widget.TextView;

public class VHStringView extends ViewHolderBasic{
	private static final int LAYOUT = R.layout.grid_cell_string_view;
	private static final int TEXTVIEW = R.id.text_view;
	
	protected int mTextViewID = TEXTVIEW;
	protected TextView mTextView;
	private GVDataStringGetter mGetter;
	
	public VHStringView() {
		super(LAYOUT);
		initDefaultGetter();
	}
	
	public VHStringView(GVDataStringGetter getter) {
		super(LAYOUT);
		mGetter = getter;
	}
	
	public VHStringView(int layoutID, int textViewID) {
		super(layoutID);
		mTextViewID = textViewID;
		initDefaultGetter();
	}

	public VHStringView(int layoutID, int textViewID, GVDataStringGetter getter) {
		super(layoutID);
		mTextViewID = textViewID;
		mGetter = getter;
	}
	
	private void initDefaultGetter() {
		mGetter = new GVDataStringGetter() {
			@Override
			public String getString(GVData data) {
				GVString string = (GVString)data;
				return string != null? string.toString() : null;
			}
		};
	}
	
	@Override
	protected void initViewsToUpdate() {
		mTextView = (TextView)getView(mTextViewID);
	}
		
	@Override
	public View updateView(GVData data) {
		mTextView.setText(mGetter.getString(data));
		return mInflated;
	}
	
	public interface GVDataStringGetter {
		public String getString(GVData data);
	}
}