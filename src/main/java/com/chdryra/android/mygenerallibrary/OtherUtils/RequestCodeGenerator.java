/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.OtherUtils;

/**
 * Created by: Rizwan Choudrey
 * On: 30/08/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class RequestCodeGenerator {
    private static final int MAX_16_BIT = 65535;

    public static int getCode(String tag) {
        int code = tag.hashCode();
        code = code >= 0 ? code : -code;
        if(code > MAX_16_BIT) code = MAX_16_BIT;
        return code;
    }

    public static int getCode(Class<?> clazz) {
        return getCode(clazz.getName());
    }

    public static int getCode(Class<?> clazz, String tag) {
        return getCode(clazz.getName() + "." + tag);
    }
}
