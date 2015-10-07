/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

/**
 * Wrapper class for Strings that implements {@link ViewHolderData}.
 */
public class VHDString implements ViewHolderData {
    private final String mString;

    //Constructors
    public VHDString(String string) {
        mString = string;
    }

    protected VHDString() {
        mString = null;
    }

    public String get() {
        return mString;
    }

    //Overridden
    @Override
    public ViewHolder getViewHolder() {
        return new VHString();
    }

    @Override
    public boolean isValidForDisplay() {
        return mString != null && mString.length() > 0;
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
        VHDString other = (VHDString) obj;
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
}


