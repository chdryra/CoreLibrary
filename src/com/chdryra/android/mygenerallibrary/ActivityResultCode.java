/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

/**
 * Encapsulates integers representing return flags for activities returning results to other
 * activities, fragments etc.
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
 * <p>
 * The static
 * <pre>
 *          <code>get(int resultCode)</code>
 *         </pre>
 * method finds the ActivityResultCode for an integer result code passed back.
 * </p>
 *
 * <p>
 * and the
 * <pre>
 *          <code>equals(int resultCode)</code>
 *          <code>equals(ActivityResultCode resultCode)</code>
 *         </pre>
 * methods can compare result codes.
 * </p>
 *
 * </p>
 */
public enum ActivityResultCode {
    CANCEL(0), DONE(1), OTHER(2), EDIT(3), ADD(4), DELETE(5), CLEAR(6), OK(7), UP(8), YES(9),
    NO(10);

    private final int mValue;

    private ActivityResultCode(int value) {
        this.mValue = value;
    }

    /**
     * Returns ActivityResultCode for a given integer.
     *
     * @param resultCode: integer to find equivalent ActivityResultCode for.
     * @return ActivityResultCode: representing the integer resultCode, or null if not found.
     */
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

    /**
     * Checks if integer resultCode is equivalent to ActivityResultCode enum.
     *
     * @param resultCode: integer to check.
     * @return boolean: true if equivalent, false otherwise.
     */
    public boolean equals(int resultCode) {
        return mValue == resultCode;
    }

    /**
     * Checks if two ActivityResultCodes equal each other.
     *
     * @param resultCode: ActivityResultCode to check.
     * @return boolean: true if equivalent, false otherwise.
     */
    public boolean equals(ActivityResultCode resultCode) {
        return mValue == resultCode.get();
    }

    /**
     * Returns the encapsulated integer.
     *
     * @return int: the encapsulated integer for the ActivityResultCode enum.
     */
    public int get() {
        return mValue;
    }
}
