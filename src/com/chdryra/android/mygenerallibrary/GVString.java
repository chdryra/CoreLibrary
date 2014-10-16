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
 * Wrapper class for Strings that implements GVData.
 */
public class GVString implements GVData {
    public static final Parcelable.Creator<GVString> CREATOR = new Parcelable.Creator<GVString>
            () {
        public GVString createFromParcel(Parcel in) {
            return new GVString(in);
        }

        public GVString[] newArray(int size) {
            return new GVString[size];
        }
    };
    private final String mString;

    public GVString(String string) {
        mString = string;
    }

    public GVString(Parcel in) {
        mString = in.readString();
    }

    @Override
    public ViewHolder getViewHolder() {
        return new VHStringView();
    }

    @Override
    public boolean isValidForDisplay() {
        return mString != null && mString.length() > 0;
    }

    public String get() {
        return mString;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GVString other = (GVString) obj;
        if (mString == null) {
            if (other.mString != null) {
                return false;
            }
        } else if (!mString.equals(other.mString)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mString == null) ? 0 : mString.hashCode());
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mString);
    }
}


