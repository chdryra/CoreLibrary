/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Viewholder;

/**
 * Wrapper class for 2 Strings that implements {@link ViewHolderData}. Used for viewing upper and
 * lower text in a list item using the
 * {@link ViewHolderAdapter} framework.
 */
public class VHDDualString implements ViewHolderData {
    private final String mUpper;
    private final String mLower;

    //Constructors
    public VHDDualString(String upper, String lower) {
        mUpper = upper;
        mLower = lower;
    }

    protected VHDDualString() {
        mUpper = "";
        mLower = "";
    }

    //public methods
    public String getUpper() {
        return mUpper;
    }

    public String getLower() {
        return mLower;
    }

    //Overridden
    @Override
    public ViewHolder getViewHolder() {
        return new VHDualString();
    }

    @Override
    public boolean isValidForDisplay() {
        return (mUpper != null && mUpper.length() > 0 && mLower != null && mLower.length() > 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VHDDualString)) return false;

        VHDDualString that = (VHDDualString) o;

        if (!mUpper.equals(that.mUpper)) return false;
        return mLower.equals(that.mLower);

    }

    @Override
    public int hashCode() {
        int result = mUpper.hashCode();
        result = 31 * result + mLower.hashCode();
        return result;
    }
}
