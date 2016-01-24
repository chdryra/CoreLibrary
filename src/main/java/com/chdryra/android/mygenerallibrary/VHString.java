/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
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
    private static final int LAYOUT = R.layout.grid_cell_string_view;
    private static final int TEXTVIEW = R.id.text_view;

    private int mTextViewId = TEXTVIEW;
    private TextView mTextView;
    private VHDataStringGetter mGetter;

    /**
     * Simple interface for extracting strings from data more complex than {@link com.chdryra
     * .android.mygenerallibrary.VHDString} in order to use the {@link
     * com.chdryra.android.mygenerallibrary.VHString} ViewHolder. For example,
     * simple text display cells in the {@link com.chdryra.android.mygenerallibrary
     * .ViewHolderAdapter}
     * framework.
     */
    public interface VHDataStringGetter {
        //abstract
        public String getString(ViewHolderData data);
    }

    //Constructors
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

    //Overridden
    @Override
    public void updateView(ViewHolderData data) {
        if (mTextView == null) mTextView = (TextView) getView(mTextViewId);
        if (data != null) mTextView.setText(mGetter.getString(data));
    }
}