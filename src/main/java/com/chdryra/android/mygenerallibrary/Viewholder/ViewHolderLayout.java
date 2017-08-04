/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Viewholder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.chdryra.android.mygenerallibrary.R;

/**
 * Explicit nomenclature for a style to use as views in a ListView-type view utilising the
 * {@link ViewHolderAdapter} framework. Declared so that it can be clearly used in XML layout
 * files when defining the look of a view holder.
 */
public class ViewHolderLayout extends CardView {
    public ViewHolderLayout(Context context) {
        super(context);
    }

    public ViewHolderLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ViewHolderLayout(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.viewHolderStyle);
    }
}
