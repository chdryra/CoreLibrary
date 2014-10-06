/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

/**
 * Interface to allow a function pointer to be defined as anonymous inner classes.
 *
 * @param <T>: data the function needs to execute.
 */
public interface FunctionPointer<T> {
    public void execute(T data);
}
