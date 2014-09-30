/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.view.View;
import android.widget.TextView;

public class VHStringView extends ViewHolderBasic {
    private static final int LAYOUT   = R.layout.grid_cell_string_view;
    private static final int TEXTVIEW = R.id.text_view;

    private int mTextViewID = TEXTVIEW;
    private TextView           mTextView;
    private GVDataStringGetter mGetter;

    public VHStringView() {
        super(LAYOUT);
        initDefaultGetter();
    }

    private void initDefaultGetter() {
        mGetter = new GVDataStringGetter() {
            @Override
            public String getString(GVData data) {
                GVString string = (GVString) data;
                return string != null ? string.toString() : null;
            }
        };
    }

    public VHStringView(int layoutID, int textViewID) {
        super(layoutID);
        mTextViewID = textViewID;
        initDefaultGetter();
    }

    public VHStringView(int layoutID, int textViewID, GVDataStringGetter getter) {
        super(layoutID);
        mTextViewID = textViewID;
        mGetter = getter;
    }

    @Override
    public View updateView(GVData data) {
        mTextView.setText(mGetter.getString(data));
        return mInflated;
    }

    @Override
    protected void initViewsToUpdate() {
        mTextView = (TextView) getView(mTextViewID);
    }

    public interface GVDataStringGetter {
        public String getString(GVData data);
    }
}