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
public class DifferenceFloat implements DifferenceLevel<DifferenceFloat> {
    private final float mValue;

    public DifferenceFloat(float value) {
        mValue = value;
    }

    @Override
    public boolean lessThanOrEqualTo(DifferenceFloat differenceThreshold) {
        return mValue <= differenceThreshold.mValue;
    }
}
