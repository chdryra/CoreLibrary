/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

public enum ActivityResultCode {
	CANCEL(0), DONE(1), OTHER(2), EDIT(3), ADD(4), DELETE(5), CLEAR(6), OK(7), UP(8), YES(9), NO(10);

	private final int mValue;

	private ActivityResultCode(int value) {
		this.mValue = value;
	}

	public int get() {
		return mValue;
	}

	public static ActivityResultCode get(int resultCode) {
		ActivityResultCode returnCode = null;
		for (ActivityResultCode code : ActivityResultCode.values()) {
			if (code.equals(resultCode))
			{
				returnCode = code;
				break;
			}
		}

		return returnCode;
	}

	public boolean equals(ActivityResultCode resultCode) {
		return mValue == resultCode.get();
	}

	public boolean equals(int resultCode) {
		return mValue == resultCode;
	}
}
