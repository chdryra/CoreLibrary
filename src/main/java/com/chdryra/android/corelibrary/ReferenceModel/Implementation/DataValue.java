/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chdryra.android.corelibrary.AsyncUtils.CallbackMessage;

/**
 * Created by: Rizwan Choudrey
 * On: 28/07/2016
 * Email: rizwan.choudrey@gmail.com
 */
public class DataValue<T> {
    private final CallbackMessage mMessage;
    private T mData;

    public DataValue(@Nullable T data) {
        mData = data;
        mMessage = data != null ? CallbackMessage.ok() : getError();
    }

    public DataValue(CallbackMessage message) {
        mMessage = message;
    }

    public DataValue() {
        mMessage = getError();
    }

    public T getData() {
        return mData;
    }

    public CallbackMessage getMessage() {
        return mMessage;
    }

    public boolean hasValue() {
        return mData != null && !mMessage.isError();
    }

    @NonNull
    private CallbackMessage getError() {
        return CallbackMessage.error("Invalid reference");
    }
}
