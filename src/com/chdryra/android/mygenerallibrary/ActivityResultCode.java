/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

/**
 * Encapsulates integers representing return flags. Usually for activities and fragments returning
 * results to other activities, fragments etc.
 * <p/>
 * <p>
 * Activities often start other activities using <code>startActivityForResult(.)</code>and
 * expect an integer result code back via <code>onActivityResult(.)</code>. This result code
 * indicates some state selected, changed etc. The int result codes are usually defined within
 * the commissioning/result-generating activities themselves. However, having int flags all over
 * the place often representing similar choices, options etc. gets messy very quickly and can
 * be a ripe source of bugs.
 * </p>
 * <p>
 * This enum wraps integers to represent common result codes. If the commissioning activity and
 * result-generating activity only ever use and refer to ActivityResultCode to generate and
 * interrogate result codes, neither has to care about the integers themselves and what they
 * represent.
 * </p>
 * <p/>
 * <p>
 * The static <code>get(int resultCode)</code> method finds the ActivityResultCode for an integer
 * result code passed back to, say, <code>onActivityResult(.)</code>. The <code>equals(.)</code>
 * methods can compare result codes.
 * </p>
 */
public enum ActivityResultCode {
    CANCEL(0), DONE(1), OTHER(2), EDIT(3), ADD(4), DELETE(5), CLEAR(6), OK(7), UP(8), YES(9),
    NO(10);

    private final int mValue;

    private ActivityResultCode(int value) {
        this.mValue = value;
    }

    public static ActivityResultCode get(int resultCode) {
        ActivityResultCode returnCode = null;
        for (ActivityResultCode code : ActivityResultCode.values()) {
            if (code.equals(resultCode)) {
                returnCode = code;
                break;
            }
        }

        return returnCode;
    }

    public boolean equals(int resultCode) {
        return mValue == resultCode;
    }

    public boolean equals(ActivityResultCode resultCode) {
        return mValue == resultCode.get();
    }

    public int get() {
        return mValue;
    }
}
