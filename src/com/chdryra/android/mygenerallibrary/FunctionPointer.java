/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

/**
 * Interface to allow a function pointer to be defined as an anonymous inner classes.
 *
 * @param <T1>: data type the passed into the function.
 * @param <T2>: type returned by execution of function.
 */
public interface FunctionPointer<T1, T2> {
    public T2 execute(T1 data);
}
