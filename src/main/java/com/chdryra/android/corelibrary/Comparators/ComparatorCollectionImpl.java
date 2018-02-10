/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Comparators;

import com.chdryra.android.corelibrary.Collections.CollectionIdableImpl;

import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 10/08/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class ComparatorCollectionImpl<T> extends CollectionIdableImpl<String, NamedComparator<T>> implements ComparatorCollection<T>{
    public ComparatorCollectionImpl(NamedComparator<T> defaultItem) {
        super(defaultItem);
    }

    public ComparatorCollectionImpl(Collection<NamedComparator<T>> items) {
        super(items);
    }

    @Override
    public NamedComparator<T> getDefault() {
        return iterator().next();
    }
}
