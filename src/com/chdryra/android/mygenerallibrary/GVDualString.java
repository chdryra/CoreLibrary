/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

/**
 * GridViewable data viewed as an upper string and lower string in a GridView cell.
 */
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

    @Override
    public boolean isValidForDisplay() {
        return (mUpper != null && mUpper.length() > 0) || (mLower != null && mLower.length() > 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GVDualString)) return false;

        GVDualString that = (GVDualString) o;

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
