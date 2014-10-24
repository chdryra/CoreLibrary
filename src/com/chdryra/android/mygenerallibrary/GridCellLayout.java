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
 * Explicit nomenclature for a style to use as cells in a GridView utilising the
 * {@link GridViewCellAdapter} framework. Declared so that it can be clearly used in XML layout
 * files when defining the look of a grid cell.
 */
class GridCellLayout extends FrameLayout {

    public GridCellLayout(Context context) {
        super(context);
    }

    public GridCellLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GridCellLayout(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.gridCellStyle);
    }
}
