/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.LocationUtils;

import com.chdryra.android.corelibrary.NumberUtils.NumberFormatter;

/**
 * Created by: Rizwan Choudrey
 * On: 12/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class DistanceFormatter extends NumberFormatter {
    private static final double METRES_IN_FEET = 0.3048;
    private static final double METRES_IN_MILE = 1609.344;

    public enum MetricImperial {
        METRIC(1.0, 1000.0, "m", "km"),
        IMPERIAL(METRES_IN_FEET, METRES_IN_MILE, "ft", "mi");

        private double mShort;
        private double mLong;
        private String mShortUnit;
        private String mLongUnit;

        private MetricImperial(double shortDistanceInMetres, double longDistanceInMetres,
                               String shortUnit, String longUnit) {
            mShort = shortDistanceInMetres;
            mLong = longDistanceInMetres;
            mShortUnit = shortUnit;
            mLongUnit = longUnit;
        }

        private boolean isLong(double distanceInMetres) {
            return distanceInMetres > mLong;
        }

        private String getUnit(double distanceInMetres) {
            return isLong(distanceInMetres) ? mLongUnit : mShortUnit;
        }
    }

    //Static methods
    public static String formatMetreDistance(double metres, MetricImperial metricImperial) {
        boolean longMeasure = metricImperial.isLong(metres);
        double num = metres / (longMeasure ? metricImperial.mLong : metricImperial.mShort);
        int sigDigits = numSignificantDigits(num);
        int decDigits = numDecimalDigits(num);

        String formatted = roundToSignificant(num, sigDigits, true);
        if (longMeasure && sigDigits == 1 && decDigits > 0) formatted = roundToSignificant(num, 2, true);

        formatted += metricImperial.getUnit(metres);

        return formatted;
    }
}
