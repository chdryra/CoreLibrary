/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 17 October, 2014
 */

package com.chdryra.android.mygenerallibrary;

/**
 * Created by: Rizwan Choudrey
 * On: 17/10/2014
 * Email: rizwan.choudrey@gmail.com
 */

/**
 * Items that can be in a collection that facilitates the ViewHolder pattern.
 * <p>
 * Provides a ViewHolder and know if the wrapped data is displayable, for example
 * nonNull, has appropriate length/size etc.
 * </p>
 * <p>
 * Should also provide appropriate equals/hashcode implementations to ensure correct
 * lookup in a collection.
 * </p>
 *
 * @see com.chdryra.android.mygenerallibrary.ViewHolder
 */
public interface ViewHolderData {

    public ViewHolder getViewHolder();

    public boolean isValidForDisplay();

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();
}

