/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

/**
 * Wrapper class for 2 Strings that implements {@link ViewHolderData}. Used for viewing upper and
 * lower text in a list item using the
 * {@link ViewHolderAdapter} framework.
 */
public class VHDDualString implements ViewHolderData {
    private final String mUpper;
    private final String mLower;

    protected VHDDualString() {
        mUpper = null;
        mLower = null;
    }

    public VHDDualString(String upper, String lower) {
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
    public ViewHolder newViewHolder() {
        return new VHDualString();
    }

    @Override
    public boolean isValidForDisplay() {
        return (mUpper != null && mUpper.length() > 0) || (mLower != null && mLower.length() > 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VHDDualString)) return false;

        VHDDualString that = (VHDDualString) o;

        return !(mLower != null ? !mLower.equals(that.mLower) : that.mLower != null) && !(mUpper
                != null ? !mUpper.equals(that.mUpper) : that.mUpper != null);

    }

    @Override
    public int hashCode() {
        int result = mUpper != null ? mUpper.hashCode() : 0;
        result = 31 * result + (mLower != null ? mLower.hashCode() : 0);
        return result;
    }
}
