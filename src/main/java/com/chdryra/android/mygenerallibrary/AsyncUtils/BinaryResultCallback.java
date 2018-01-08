/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.AsyncUtils;

/**
 * Created by: Rizwan Choudrey
 * On: 02/03/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface BinaryResultCallback<Success, Failure> {
    void onSuccess(Success result);

    void onFailure(Failure result);
}
