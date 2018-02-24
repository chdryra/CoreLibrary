/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

import android.support.annotation.Nullable;

import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.Size;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 21/08/2016
 * Email: rizwan.choudrey@gmail.com
 */
public abstract class CollectionReferenceBasic<T, C extends Collection<T>, S extends Size> extends
        SubscribableReferenceBasic<C>
        implements SubscribersManager.SubscribableCollectionReference<T, C, S> {
    private final Collection<ItemSubscriber<T>> mItemSubscribers;
    private final Collection<ValueSubscriber<C>> mValueSubscribers;
    private final SubscribersManager<T, S> mDelegate;

    protected abstract void onBinding(ItemSubscriber<T> subscriber);

    protected abstract void onUnbinding(ItemSubscriber<T> subscriber);

    protected CollectionReferenceBasic() {
        mValueSubscribers = new ArrayList<>();
        mItemSubscribers = new ArrayList<>();
        mDelegate = new SubscribersManager<>(this);
    }

    @Nullable
    @Override
    protected abstract C getNullValue();

    @Override
    protected Collection<ValueSubscriber<C>> getSubscribers() {
        return mValueSubscribers;
    }

    protected boolean hasItemBinders() {
        return mItemSubscribers.size() > 0;
    }

    protected void notifyOnAdded(C data) {
        for (ItemSubscriber<T> binder : mItemSubscribers) {
            notifyOnAdded(binder, data);
        }

        notifyValueSubscribers();
    }

    protected void notifyOnAdded(T item) {
        for (ItemSubscriber<T> binder : mItemSubscribers) {
            binder.onItemAdded(item);
        }

        notifyValueSubscribers();
    }

    protected void notifyOnAdded(ItemSubscriber<T> binder, C data) {
        for (T reference : data) {
            binder.onItemAdded(reference);
        }
    }

    protected void notifyOnRemoved(C data) {
        for (ItemSubscriber<T> binder : mItemSubscribers) {
            for (T reference : data) {
                binder.onItemRemoved(reference);
            }
        }

        notifyValueSubscribers();
    }

    protected void notifyOnRemoved(T item) {
        for (ItemSubscriber<T> binder : mItemSubscribers) {
            binder.onItemRemoved(item);
        }

        notifyValueSubscribers();
    }

    protected void notifyAllSubscribers() {
        if (getSubscribers().size() > 0 || hasItemBinders()) {
            dereference(new DereferenceCallback<C>() {
                @Override
                public void onDereferenced(DataValue<C> value) {
                    if (value.hasValue()) {
                        notifyValueSubscribers(value.getData());
                        notifyOnCollectionChanged(value.getData());
                    }
                }
            });
        }
    }

    @Override
    protected void bind(final ValueSubscriber<C> subscriber) {
        mValueSubscribers.add(subscriber);
        notifyValueSubscriber(subscriber);
    }

    @Override
    protected void onInvalidate() {
        super.onInvalidate();
        mDelegate.notifyOnInvalidated();
        mItemSubscribers.clear();
        mValueSubscribers.clear();
    }

    @Override
    protected boolean contains(ValueSubscriber<C> subscriber) {
        return mValueSubscribers.contains(subscriber);
    }

    @Override
    protected void removeSubscriber(ValueSubscriber<C> subscriber) {
        mValueSubscribers.remove(subscriber);
    }

    @Override
    public void unsubscribe(ItemSubscriber<T> binder) {
        mDelegate.unsubscribe(binder);
    }

    @Override
    public void subscribe(final ItemSubscriber<T> binder) {
        mDelegate.subscribe(binder);
    }

    @Override
    public Collection<ItemSubscriber<T>> getItemSubscribers() {
        return mItemSubscribers;
    }

    @Override
    public void unbindSubscriber(ItemSubscriber<T> subscriber) {
        if (containsSubscriber(subscriber)) {
            mItemSubscribers.remove(subscriber);
            onUnbinding(subscriber);
        }
    }

    @Override
    public void bindSubscriber(final ItemSubscriber<T> subscriber) {
        if (!containsSubscriber(subscriber)) {
            mItemSubscribers.add(subscriber);
            onBinding(subscriber);
        }
    }

    @Override
    public boolean containsSubscriber(ItemSubscriber<T> subscriber) {
        return mItemSubscribers.contains(subscriber);
    }

    private void notifyOnCollectionChanged(C items) {
        for (ItemSubscriber<T> subscriber : mItemSubscribers) {
            subscriber.onCollectionChanged(items);
        }

        notifyValueSubscribers(items);
    }

    private void notifyValueSubscribers(C data) {
        for (ValueSubscriber<C> subscriber : mValueSubscribers) {
            subscriber.onReferenceValue(data);
        }
    }
}
