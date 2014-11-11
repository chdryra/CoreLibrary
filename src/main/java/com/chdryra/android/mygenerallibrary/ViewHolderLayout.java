/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 30 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Explicit nomenclature for a style to use as views in a ListView-type view utilising the
 * {@link ViewHolderAdapter} framework. Declared so that it can be clearly used in XML layout
 * files when defining the look of a view holder.
 */
public class ViewHolderLayout extends FrameLayout {

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
