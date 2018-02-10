/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Viewholder;

import com.chdryra.android.corelibrary.R;

/**
 * Simple {@link ViewHolder} for
 * {@link VHDDualString}. Displays an upper and lower string.
 */
public class VHDualString extends ViewHolderBasic {
    private static final int LAYOUT = R.layout.grid_cell_dual_string;
    private static final int UPPER = R.id.upper_text_view;
    private static final int LOWER = R.id.lower_text_view;

    private int mUpperID = UPPER;
    private int mLowerID = LOWER;

    //Constructors
    public VHDualString() {
        this(LAYOUT, UPPER, LOWER);
    }

    protected VHDualString(int layoutID, int upperTextViewID, int lowerTextViewID) {
        super(layoutID, new int[]{upperTextViewID, lowerTextViewID});
        mUpperID = upperTextViewID;
        mLowerID = lowerTextViewID;
    }

    //Overridden
    @Override
    public void updateView(ViewHolderData data) {
        VHDDualString dual = (VHDDualString) data;
        if (dual != null && dual.isValidForDisplay()) {
            setText(mUpperID, dual.getUpper());
            setText(mLowerID, dual.getLower());
        }
    }
}
