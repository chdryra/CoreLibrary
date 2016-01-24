/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
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
 * Provides a {@link ViewHolder} and knows if the wrapped data is displayable, for example
 * nonNull, has appropriate length/size etc.
 * </p>
 * <p>
 * Should also provide appropriate equals/hashcode implementations to ensure correct
 * lookup in a collection.
 * </p>
 */
public interface ViewHolderData {

    //abstract
    public ViewHolder getViewHolder();

    public boolean isValidForDisplay();

    //Overridden
    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();
}

