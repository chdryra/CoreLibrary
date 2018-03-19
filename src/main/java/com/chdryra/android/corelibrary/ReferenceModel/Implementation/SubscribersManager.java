/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.CollectionReference;
import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.Size;

import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 21/08/2016
 * Email: rizwan.choudrey@gmail.com
 */
public class SubscribersManager<T, S extends Size> {
    private final SubscribableCollectionReference<T, ?, S> mReference;

    public interface SubscribableCollectionReference<Value, C extends Collection<Value>, S
            extends Size> extends CollectionReference<Value, C, S> {
        boolean containsSubscriber(ItemSubscriber<Value> subscriber);

        void bindSubscriber(ItemSubscriber<Value> subscriber);

        void unbindSubscriber(ItemSubscriber<Value> subscriber);

        Collection<ItemSubscriber<Value>> getItemSubscribers();
    }

    public SubscribersManager(SubscribableCollectionReference<T, ?, S> reference) {
        mReference = reference;
    }

    public void subscribe(final CollectionReference.ItemSubscriber<T> subscriber) {
        if (!mReference.isValidReference()) {
            subscriber.onInvalidated(mReference);
        } else if (!mReference.containsSubscriber(subscriber)) {
            mReference.bindSubscriber(subscriber);
        }
    }

    public void unsubscribe(CollectionReference.ItemSubscriber<T> subscriber) {
        if (mReference.containsSubscriber(subscriber)) mReference.unbindSubscriber(subscriber);
    }

    public void notifyOnInvalidated() {
        for (CollectionReference.ItemSubscriber<T> binder : mReference.getItemSubscribers()) {
            binder.onInvalidated(mReference);
        }
    }
}
