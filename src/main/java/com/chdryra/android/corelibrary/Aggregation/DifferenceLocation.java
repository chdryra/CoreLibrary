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
 * On: 15/07/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class DifferenceLocation implements DifferenceLevel<DifferenceLocation> {
    private final DifferenceFloat mDistanceDifference;
    private final DifferencePercentage mNameDifference;

    public DifferenceLocation(DifferenceFloat distanceDifference, DifferencePercentage
            nameDifference) {
        mDistanceDifference = distanceDifference;
        mNameDifference = nameDifference;
    }

    @Override
    public boolean lessThanOrEqualTo(DifferenceLocation differenceThreshold) {
        return mDistanceDifference.lessThanOrEqualTo(differenceThreshold.mDistanceDifference) &&
                mNameDifference.lessThanOrEqualTo(differenceThreshold.mNameDifference);
    }
}
