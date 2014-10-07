/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import com.chdryra.android.mygenerallibrary.GridViewCellAdapter.GridViewable;

/**
 * Collection for GVData objects that facilitate the ViewHolder pattern. The <code>sort()</code>
 * requirement of GridViewable is provided by the base class SortableList<T>.
 *
 * @param <T>: a type that implements GVData.
 * @see com.chdryra.android.mygenerallibrary.GridViewCellAdapter
 */
public abstract class GVList<T extends GVData> extends SortableList<T> implements GridViewable<T> {

    @Override
    public ViewHolder getViewHolder(int position) {
        return getItem(position).getViewHolder();
    }
}
