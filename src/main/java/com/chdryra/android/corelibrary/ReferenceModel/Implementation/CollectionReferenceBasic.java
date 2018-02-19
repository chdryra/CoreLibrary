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
    private final Collection<ItemSubscriber<T>> mItemBinders;
    private final Collection<ValueSubscriber<C>> mValueBinders;
    private final SubscribersManager<T, S> mDelegate;

    protected abstract void fireForBinder(ItemSubscriber<T> binder);

    protected CollectionReferenceBasic() {
        mValueBinders = new ArrayList<>();
        mItemBinders = new ArrayList<>();
        mDelegate = new SubscribersManager<>(this);
    }

    @Nullable
    @Override
    protected abstract C getNullValue();

    @Override
    protected Collection<ValueSubscriber<C>> getSubscribers() {
        return mValueBinders;
    }

    @Override
    protected void bind(final ValueSubscriber<C> subscriber) {
        mValueBinders.add(subscriber);
        dereference(new DereferenceCallback<C>() {
            @Override
            public void onDereferenced(DataValue<C> value) {
                if (value.hasValue()) subscriber.onReferenceValue(value.getData());
            }
        });
    }

    @Override
    protected void onInvalidate() {
        super.onInvalidate();
        mDelegate.notifyOnInvalidated();
        mItemBinders.clear();
        mValueBinders.clear();
    }

    protected boolean hasItemBinders() {
        return mItemBinders.size() > 0;
    }

    private boolean hasValueBinders() {
        return mValueBinders.size() > 0;
    }

    @Override
    protected boolean contains(ValueSubscriber<C> subscriber) {
        return mValueBinders.contains(subscriber);
    }

    @Override
    protected void removeSubscriber(ValueSubscriber<C> subscriber) {
        mValueBinders.remove(subscriber);
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
        return mItemBinders;
    }

    @Override
    public void unbindSubscriber(ItemSubscriber<T> binder) {
        mItemBinders.remove(binder);
    }

    @Override
    public void bindSubscriber(final ItemSubscriber<T> binder) {
        if(!containsSubscriber(binder)) {
            mItemBinders.add(binder);
            fireForBinder(binder);
        }
    }

    @Override
    public boolean containsSubscriber(ItemSubscriber<T> binder) {
        return mItemBinders.contains(binder);
    }

    private void notifyOnCollectionChanged(C items) {
        for (ItemSubscriber<T> binder : mItemBinders) {
            binder.onCollectionChanged(items);
        }
    }

    protected void notifyOnAdded(C data) {
        for (ItemSubscriber<T> binder : mItemBinders) {
            notifyOnAdded(binder, data);
        }
    }

    protected void notifyOnAdded(T item) {
        for (ItemSubscriber<T> binder : mItemBinders) {
            binder.onItemAdded(item);
        }
    }

    protected void notifyOnAdded(ItemSubscriber<T> binder, C data) {
        for (T reference : data) {
            binder.onItemAdded(reference);
        }
    }

    protected void notifyOnRemoved(C data) {
        for (ItemSubscriber<T> binder : mItemBinders) {
            for (T reference : data) {
                binder.onItemRemoved(reference);
            }
        }
    }

    protected void notifyOnRemoved(T item) {
        for (ItemSubscriber<T> binder : mItemBinders) {
            binder.onItemRemoved(item);
        }
    }

    private void notifyValueBinders(C data) {
        for (ValueSubscriber<C> binder : mValueBinders) {
            binder.onReferenceValue(data);
        }
    }

    protected void notifyValueBinders() {
        if (hasValueBinders()) {
            dereference(new DereferenceCallback<C>() {
                @Override
                public void onDereferenced(DataValue<C> value) {
                    if (value.hasValue()) notifyValueBinders(value.getData());
                }
            });
        }
    }

    protected void notifyAllBinders() {
        if (hasValueBinders() || hasItemBinders()) {
            dereference(new DereferenceCallback<C>() {
                @Override
                public void onDereferenced(DataValue<C> value) {
                    if (value.hasValue()) {
                        notifyValueBinders(value.getData());
                        notifyOnCollectionChanged(value.getData());
                    }
                }
            });
        }
    }
}
