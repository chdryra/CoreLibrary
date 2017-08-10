/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Comparators;

import java.util.Comparator;

/**
 * Created by: Rizwan Choudrey
 * On: 27/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class LongComparator implements Comparator<Long> {
    @Override
    public int compare(Long lhs, Long rhs) {
        return lhs < rhs ? -1 : lhs > rhs ? 1 : 0;
    }
}
