/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Interfaces;

import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 28/07/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface CollectionReference<T, C extends Collection<T>, S extends Size> extends DataReference<C> {
    void subscribe(ItemSubscriber<T> binder);

    void unsubscribe(ItemSubscriber<T> binder);

    DataReference<S> getSize();

    interface ItemSubscriber<T> {
        void onItemAdded(T item);

        void onItemRemoved(T item);

        void onCollectionChanged(Collection<T> newItems);

        void onInvalidated(CollectionReference<T, ?, ?> reference);
    }
}
