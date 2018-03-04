/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

import android.support.annotation.NonNull;

import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.CollectionReference;
import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.DataReference;
import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.Size;

import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 15/02/2018
 * Email: rizwan.choudrey@gmail.com
 */

public class SizeReference<T, C extends Collection<T>> extends SubscribableReference<Size> implements
        CollectionReference.ItemSubscriber<T>, DataReference<Size> {
    private final CollectionReference<T, C, Size> mReference;
    private int mSize = 0;
    private boolean mIsBound = false;
    private boolean mIsValid = true;

    SizeReference(CollectionReference<T, C, Size> reference) {
        mReference = reference;
    }

    @Override
    protected void doDereferencing(final DereferenceCallback<Size> callback) {
        mReference.dereference(new DereferenceCallback<C>() {
            @Override
            public void onDereferenced(DataValue<C> value) {
                int size = value.hasValue() ? value.getData().size() : 0;
                callback.onDereferenced(new DataValue<Size>(getSize(size)));
            }
        });
    }

    @Override
    public void onItemAdded(T item) {
        ++mSize;
        notifyBinders();
    }

    @Override
    public void onItemRemoved(T item) {
        --mSize;
        notifyBinders();
    }

    @Override
    public void onCollectionChanged(Collection<T> newItems) {
        mSize = newItems.size();
        notifyBinders();
    }

    @Override
    public void onInvalidated(CollectionReference<T, ?, ?> reference) {
        onInvalidate();
    }

    @Override
    protected void onInvalidate() {
        mSize = 0;
        mReference.unsubscribe(this);
        mIsBound = false;
        mIsValid = false;
        super.onInvalidate();
    }

    @Override
    protected void notifySubscriber(ValueSubscriber<Size> subscriber) {
        if (!mIsValid) {
            subscriber.onInvalidated(this);
            return;
        }

        subscriber.onReferenceValue(getSize(mSize));

        if (!mIsBound) {
            mReference.subscribe(this);
            mIsBound = true;
        }
    }

    @NonNull
    private SizeImpl getSize(int size) {
        return new SizeImpl(size);
    }

    private void notifyBinders() {
        for (ValueSubscriber<Size> binder : getSubscribers()) {
            binder.onReferenceValue(getSize(mSize));
        }
    }
}
