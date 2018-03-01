/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

/**
 * Created by: Rizwan Choudrey
 * On: 01/03/2018
 * Email: rizwan.choudrey@gmail.com
 */

public class DataReferenceWrapper<T> extends DereferencableBasic<T> {
    private T mData;

    public DataReferenceWrapper(T data) {
        mData = data;
    }

    public void setData(T data) {
        mData = data;
        notifySubscribers();
    }

    @Override
    protected void doDereferencing(DereferenceCallback<T> callback) {
        callback.onDereferenced(new DataValue<>(mData));
    }
}
