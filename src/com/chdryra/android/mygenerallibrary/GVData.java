/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.os.Parcelable;

/**
 * Items that expect to be in a GridViewable collection and want to provide their own ViewHolder.
 *
 * @see com.chdryra.android.mygenerallibrary.GridViewCellAdapter.GridViewable
 * @see com.chdryra.android.mygenerallibrary.GridViewCellAdapter
 */
public interface GVData extends Parcelable {

    public ViewHolder getViewHolder();

    /**
     * Check to see if wrapped data is considered valid for display, for example nonNull,
     * has length/size etc.
     *
     * @return boolean: wrapped data passes checks.
     */
    public boolean isValidForDisplay();

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();
}
