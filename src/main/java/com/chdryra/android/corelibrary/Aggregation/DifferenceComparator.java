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
 * On: 25/06/2015
 * Email: rizwan.choudrey@gmail.com
 */
public interface DifferenceComparator<T, S extends DifferenceLevel<?>> {
    S compare(T lhs, T rhs);
}
