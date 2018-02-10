/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Viewholder;


import com.chdryra.android.corelibrary.Collections.SortableList;

/**
 * An iterable, sortable collection of {@link ViewHolderData} objects that facilitate the
 * {@link ViewHolder}
 * pattern.
 *
 * @param <T>: a type that implements {@link ViewHolderData}.
 */
public interface ViewHolderDataList<T extends ViewHolderData> extends SortableList<T> {
}
