/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.CollectionReference;
import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.DataReference;
import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.Size;

import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 01/08/2016
 * Email: rizwan.choudrey@gmail.com
 */
public class NullDataReference<T> implements DataReference<T> {
    @Override
    public void registerListener(InvalidationListener listener) {

    }

    @Override
    public void unregisterListener(InvalidationListener listener) {

    }

    @Override
    public void dereference(DereferenceCallback<T> callback) {
        callback.onDereferenced(new DataValue<T>());
    }

    @Override
    public void subscribe(ValueSubscriber<T> subscriber) {

    }

    @Override
    public void unsubscribe(ValueSubscriber<T> subscriber) {

    }

    @Override
    public boolean isValidReference() {
        return false;
    }

    @Override
    public void invalidate() {

    }

    public static class NullList<T, C extends Collection<T>, S extends Size> extends NullDataReference<C> implements CollectionReference<T, C, S> {

        @Override
        public void subscribe(ItemSubscriber<T> binder) {

        }

        @Override
        public void unsubscribe(ItemSubscriber<T> binder) {

        }

        @Override
        public DataReference<S> getSize() {
            return new NullSize<>();
        }
    }

    private static class NullSize<S extends Size> extends NullDataReference<S> implements DataReference<S> {

    }
}
