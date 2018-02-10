/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Aggregation;

/**
 * Created by: Rizwan Choudrey
 * On: 03/07/2015
 * Email: rizwan.choudrey@gmail.com
 */

//More similar, lower level should be. 'Zero' means exactly the same.
public interface DifferenceLevel<T> {
    boolean lessThanOrEqualTo(T differenceThreshold);
}
