/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by: Rizwan Choudrey
 * On: 03/08/2017
 * Email: rizwan.choudrey@gmail.com
 *
 * From here: https://stackoverflow.com/questions/28531996/android-recyclerview-gridlayoutmanager-column-spacing
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount;
            outRect.right = (column + 1) * spacing / spanCount;
            outRect.bottom = spacing;
            if (position < spanCount) outRect.top = spacing;
        } else {
            outRect.left = column * 2* spacing / spanCount;
            outRect.right = spacing - 2 * (column + 1) * spacing / spanCount;
            if (position >= spanCount) outRect.top = spacing;
        }
    }
}
