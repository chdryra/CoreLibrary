/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Viewholder;

import com.chdryra.android.mygenerallibrary.R;

/**
 * Simple {@link ViewHolder} for displaying strings on a grid cell. By default it assumes the data
 * will be updated using {@link VHDString} objects. However, one can construct a string display
 * for more complex {@link ViewHolderData} by specifying a
 * {@link VHDataStringGetter} that knows how to
 * extract a string from the data.
 */
public class VHString extends ViewHolderBasic {
    private static final int LAYOUT = R.layout.grid_cell_string_view;
    private static final int TEXTVIEW = R.id.text_view;

    private int mTextViewId = TEXTVIEW;
    private VHDataStringGetter mGetter;

    public VHString() {
        this(LAYOUT, TEXTVIEW);
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

    private void initDefaultGetter() {
        mGetter = new VHDataStringGetter() {
            //Overridden
            @Override
            public String getString(ViewHolderData data) {
                VHDString string = (VHDString) data;
                if(string != null && string.isValidForDisplay()) {
                    return string.getString();
                } else {
                    return data.toString();
                }
            }
        };
    }

    @Override
    public void updateView(ViewHolderData data) {
        if (data != null) updateView(mGetter.getString(data));
    }

    public void updateView(String text) {
        setText(mTextViewId, text);
    }
}