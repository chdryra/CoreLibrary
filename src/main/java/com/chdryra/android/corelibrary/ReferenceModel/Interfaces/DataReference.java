/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Interfaces;

import com.chdryra.android.corelibrary.ReferenceModel.Implementation.DataValue;

/**
 * Created by: Rizwan Choudrey
 * On: 28/07/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface DataReference<T> {
    interface DereferenceCallback<T> {
        void onDereferenced(DataValue<T> value);
    }

    interface InvalidationListener {
        void onInvalidated(DataReference<?> reference);
    }

    interface ValueSubscriber<T> {
        void onReferenceValue(T value);

        void onInvalidated(DataReference<T> reference);
    }

    void dereference(DereferenceCallback<T> callback);

    void subscribe(ValueSubscriber<T> subscriber);

    void unsubscribe(ValueSubscriber<T> subscriber);

    void registerListener(InvalidationListener listener);

    void unregisterListener(InvalidationListener listener);

    boolean isValidReference();

    void invalidate();
}
