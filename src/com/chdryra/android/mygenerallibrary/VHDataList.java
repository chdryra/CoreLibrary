/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;


/**
 * An iterable, sortable collection of ViewHolderData objects that facilitate the ViewHolder
 * pattern.
 *
 * @param <T>: a type that implements ViewHolderData.
 */
public abstract class VHDataList<T extends ViewHolderData> extends SortableList<T> {
}
