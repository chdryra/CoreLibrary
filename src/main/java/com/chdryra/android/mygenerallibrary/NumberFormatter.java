/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 12 March, 2015
 */

package com.chdryra.android.mygenerallibrary;

import java.text.DecimalFormat;

/**
 * Created by: Rizwan Choudrey
 * On: 12/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class NumberFormatter {
    public static String roundToSignificant(double num, int toDigits) {
        int digits = Math.min(toDigits, numDigits(num));
        String pattern = digits > 1 ? "0." : "0";
        while (digits-- > 1) pattern += "0";
        DecimalFormat formatter = new DecimalFormat("0");
        DecimalFormat decimalFormatter = new DecimalFormat(pattern);
        return num % 1L > 0L ? decimalFormatter.format(num) : formatter.format(num);
    }

    protected static int numDigits(double num) {
        String[] strings = split(num);
        return strings.length > 1 ? strings[0].length() + strings[1].length() : strings[0].length();
    }

    protected static int numSignificantDigits(double num) {
        String[] strings = split(num);
        return strings[0].length();
    }

    protected static int numDecimalDigits(double num) {
        String[] strings = split(num);
        return strings.length > 1 ? strings[1].length() : 0;
    }

    private static String[] split(double num) {
        String toString = String.valueOf(num);
        return toString.split("\\.");
    }
}
