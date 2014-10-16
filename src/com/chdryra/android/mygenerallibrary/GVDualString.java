/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GridViewable data viewed as an upper string and lower string in a GridView cell.
 */
public class GVDualString implements GVData {

    public static final Parcelable.Creator<GVDualString> CREATOR = new Parcelable
            .Creator<GVDualString>
            () {
        public GVDualString createFromParcel(Parcel in) {
            return new GVDualString(in);
        }

        public GVDualString[] newArray(int size) {
            return new GVDualString[size];
        }
    };
    private final String mUpper;
    private final String mLower;

    public GVDualString(String upper, String lower) {
        mUpper = upper;
        mLower = lower;
    }

    public GVDualString(Parcel in) {
        mUpper = in.readString();
        mLower = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUpper);
        parcel.writeString(mLower);
    }
}
