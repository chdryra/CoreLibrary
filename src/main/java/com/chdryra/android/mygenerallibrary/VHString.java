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
 * Simple {@link ViewHolder} for displaying strings on a grid cell. By default it assumes the data
 * will be updated using {@link VHDString} objects. However, one can construct a string display
 * for more complex {@link com.chdryra.android.mygenerallibrary.ViewHolderData} by specifying a
 * {@link com.chdryra.android.mygenerallibrary.VHString.VHDataStringGetter} that knows how to
 * extract a string from the data.
 */
public class VHString extends ViewHolderBasic {
    private static final int LAYOUT   = R.layout.grid_cell_string_view;
    private static final int TEXTVIEW = R.id.text_view;

    private int mTextViewId = TEXTVIEW;
    private TextView           mTextView;
    private VHDataStringGetter mGetter;

    public VHString() {
        super(LAYOUT, new int[]{TEXTVIEW});
        initDefaultGetter();
    }

    protected VHString(int layoutId, int textViewId) {
        super(layoutId, new int[]{textViewId});
        mTextViewId = textViewId;
        initDefaultGetter();
    }

    protected VHString(int layoutId, int textViewId, VHDataStringGetter getter) {
        super(layoutId, new int[]{textViewId});
        mTextViewId = textViewId;
        mGetter = getter;
    }

    /**
     * Simple interface for extracting strings from data more complex than {@link com.chdryra
     * .android.mygenerallibrary.VHString} in order to use the {@link
     * com.chdryra.android.mygenerallibrary.VHString} ViewHolder. For example,
     * simple text display cells in the {@link com.chdryra.android.mygenerallibrary
     * .GridViewCellAdapter} framework.
     */
    public interface VHDataStringGetter {
        public String getString(ViewHolderData data);
    }

    @Override
    public void updateView(ViewHolderData data) {
        if (mTextView == null) mTextView = (TextView) getView(mTextViewId);
        if (data != null) mTextView.setText(mGetter.getString(data));
    }

    private void initDefaultGetter() {
        mGetter = new VHDataStringGetter() {
            @Override
            public String getString(ViewHolderData data) {
                VHDString string = (VHDString) data;
                return string != null && string.isValidForDisplay() ? string.get() : null;
            }
        };
    }
}