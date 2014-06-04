package com.chdryra.android.mygenerallibrary;

public class GVDualString implements GVData {

	private String mUpper;
	private String mLower;
	
	public GVDualString() {
		
	}
	
	public GVDualString(String upper, String lower) {
		mUpper = upper;
		mLower = lower;
	}
	
	public String getUpper() {
		return mUpper;
	}
	
	public String getLower() {
		return mLower;
	}

	@Override
	public ViewHolder getViewHolder() {
		return new VHDualStringView();
	}
}
