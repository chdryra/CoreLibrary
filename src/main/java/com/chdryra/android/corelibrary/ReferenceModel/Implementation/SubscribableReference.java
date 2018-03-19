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
public abstract class SubscribableReference<T> extends SubscribableReferenceBasic<T> {
    private final Collection<ValueSubscriber<T>> mValueBinders;

    public SubscribableReference() {
        super();
        mValueBinders = new ArrayList<>();
    }

    @Override
    protected Collection<ValueSubscriber<T>> getSubscribers() {
        return mValueBinders;
    }

    @Override
    protected void removeSubscriber(ValueSubscriber<T> subscriber) {
        mValueBinders.remove(subscriber);
    }

    @Override
    protected void bind(ValueSubscriber<T> subscriber) {
        mValueBinders.add(subscriber);
        notifySubscriber(subscriber);
    }

    @Override
    protected void onInvalidate() {
        super.onInvalidate();
        mValueBinders.clear();
    }
}
