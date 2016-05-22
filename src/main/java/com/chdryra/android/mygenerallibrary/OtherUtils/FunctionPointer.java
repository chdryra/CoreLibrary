/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.OtherUtils;

/**
 * Interface to allow a function pointer to be defined as an anonymous inner classes.
 *
 * @param <T1>: data type the passed into the function.
 * @param <T2>: type returned by execution of function.
 */
public interface FunctionPointer<T1, T2> {
    T2 execute(T1 data);
}
