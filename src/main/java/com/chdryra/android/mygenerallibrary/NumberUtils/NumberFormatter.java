/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.NumberUtils;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Created by: Rizwan Choudrey
 * On: 12/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class NumberFormatter {
    //Static methods
    public static String roundToSignificant(double num, int toDigits) {
        double rounded = MathRounder.roundToSignificant(num, toDigits);
        int digits = Math.min(toDigits, numDigits(rounded));
        int sigDigits = numSignificantDigits(rounded);
        String pattern = digits > sigDigits ? "0." : "0";
        while (digits-- > sigDigits) pattern += "0";
        DecimalFormat formatter = new DecimalFormat("0");
        DecimalFormat decimalFormatter = new DecimalFormat(pattern);
        return rounded % 1L > 0L ? decimalFormatter.format(rounded) : formatter.format(rounded);
    }

    public static int numDigits(double num) {
        String[] strings = split(num);
        return strings.length > 1 ? strings[0].length() + strings[1].length() : strings[0].length();
    }

    public static int numSignificantDigits(double num) {
        String[] strings = split(num);
        return strings[0].length();
    }

    public static int numDecimalDigits(double num) {
        String[] strings = split(num);
        if (strings.length == 2) {
            String digit = strings[1];
            if (digit.equals("0")) strings = Arrays.copyOfRange(strings, 0, 0);
        }

        return strings.length > 1 ? strings[1].length() : 0;
    }

    private static String[] split(double num) {
        String toString = String.valueOf(num);
        return toString.split("\\.");
    }
}
