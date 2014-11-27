/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.widget.TextView;

/**
 * Simple {@link com.chdryra.android.mygenerallibrary.ViewHolder} for
 * {@link com.chdryra.android.mygenerallibrary.VHDDualString}. Displays an upper and lower string.
 */
public class VHDualString extends ViewHolderBasic {
    private static final int LAYOUT   = R.layout.grid_cell_dual_string;
    private static final int UPPER    = R.id.upper_text_view;
    private              int mUpperID = UPPER;
    private static final int LOWER    = R.id.lower_text_view;
    private              int mLowerID = LOWER;
    private TextView mUpper;
    private TextView mLower;

    public VHDualString() {
        this(LAYOUT, UPPER, LOWER);
    }

    protected VHDualString(int layoutID, int upperTextViewID, int lowerTextViewID) {
        super(layoutID, new int[]{upperTextViewID, lowerTextViewID});
        mUpperID = upperTextViewID;
        mLowerID = lowerTextViewID;
    }

    @Override
    public void updateView(ViewHolderData data) {
        if (mUpper == null) mUpper = (TextView) getView(mUpperID);
        if (mLower == null) mLower = (TextView) getView(mLowerID);

        VHDDualString dual = (VHDDualString) data;
        if (dual != null && dual.isValidForDisplay()) {
            mUpper.setText(dual.getUpper());
            mLower.setText(dual.getLower());
        }
    }
}
