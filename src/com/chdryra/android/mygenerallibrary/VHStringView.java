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
 * Simple ViewHolder for displaying strings on a grid cell. By default it assumes the data will
 * be updated using GVString objects. However, one can construct a string display for more
 * complex GVData by specifying a GVDataStringGetter that knows how to extract a string from the
 * data.
 */
public class VHStringView extends ViewHolderBasic {
    private static final int LAYOUT   = R.layout.grid_cell_string_view;
    private static final int TEXTVIEW = R.id.text_view;

    private int mTextViewId = TEXTVIEW;
    private TextView           mTextView;
    private GVDataStringGetter mGetter;

    public VHStringView() {
        super(LAYOUT, new int[]{TEXTVIEW});
        initDefaultGetter();
    }

    public VHStringView(int layoutId, int textViewId) {
        super(layoutId, new int[]{textViewId});
        mTextViewId = textViewId;
        initDefaultGetter();
    }

    public VHStringView(int layoutId, int textViewId, GVDataStringGetter getter) {
        super(layoutId, new int[]{textViewId});
        mTextViewId = textViewId;
        mGetter = getter;
    }

    @Override
    public void updateView(GVData data) {
        if (mTextView == null) mTextView = (TextView) getView(mTextViewId);
        if (data != null) mTextView.setText(mGetter.getString(data));
    }

    private void initDefaultGetter() {
        mGetter = new GVDataStringGetter() {
            @Override
            public String getString(GVData data) {
                GVString string = (GVString) data;
                return string != null ? string.get() : null;
            }
        };
    }

    /**
     * Simple interface for extracting strings to display on the grid cell from GVData more
     * complex than GVString.
     */
    public interface GVDataStringGetter {
        public String getString(GVData data);
    }
}