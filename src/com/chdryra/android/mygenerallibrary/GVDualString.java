/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

public class GVDualString implements GVData {

	private final String mUpper;
	private final String mLower;

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
