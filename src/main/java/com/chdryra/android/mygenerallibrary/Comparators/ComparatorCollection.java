/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Comparators;

import com.chdryra.android.mygenerallibrary.Collections.CollectionIdable;

/**
 * Created by: Rizwan Choudrey
 * On: 27/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public interface ComparatorCollection<T> extends CollectionIdable<String, NamedComparator<T>> {
    NamedComparator<T> getDefault();
}
