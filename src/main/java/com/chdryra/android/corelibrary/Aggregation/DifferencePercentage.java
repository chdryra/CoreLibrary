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
public class DifferencePercentage implements DifferenceLevel<DifferencePercentage> {
    private double mPercentage;

    public DifferencePercentage(double percentage) {
        if (percentage < 0 || percentage > 1) {
            throw new IllegalArgumentException("Percentage should be between 0 and 1!");
        }

        mPercentage = percentage;
    }

    @Override
    public boolean lessThanOrEqualTo(DifferencePercentage differenceThreshold) {
        return mPercentage <= differenceThreshold.mPercentage;
    }
}
