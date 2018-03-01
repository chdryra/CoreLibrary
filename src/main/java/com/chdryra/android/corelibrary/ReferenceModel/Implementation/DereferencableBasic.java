/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 01/08/2016
 * Email: rizwan.choudrey@gmail.com
 */
public abstract class DereferencableBasic<T> extends SubscribableReferenceBasic<T> {
    private final Collection<ValueSubscriber<T>> mSubscribers;

    public DereferencableBasic() {
        super();
        mSubscribers = new ArrayList<>();
    }

    @Override
    protected void removeSubscriber(ValueSubscriber<T> subscriber) {
        mSubscribers.remove(subscriber);
    }

    @Override
    protected void bind(ValueSubscriber<T> subscriber) {
        mSubscribers.add(subscriber);
        fireForSubscriber(subscriber);
    }

    @Override
    protected Collection<ValueSubscriber<T>> getSubscribers() {
        return mSubscribers;
    }

    @Override
    protected void onInvalidate() {
        super.onInvalidate();
        mSubscribers.clear();
    }

    @Override
    protected boolean contains(ValueSubscriber<T> subscriber) {
        return mSubscribers.contains(subscriber);
    }

    protected void fireForSubscriber(final ValueSubscriber<T> subscriber) {
        dereference(new DereferenceCallback<T>() {
            @Override
            public void onDereferenced(DataValue<T> value) {
                if (value.hasValue()) subscriber.onReferenceValue(value.getData());
            }
        });
    }

    public void notifySubscribers() {
        for(ValueSubscriber<T> subscriber : mSubscribers) {
            fireForSubscriber(subscriber);
        }
    }
}
