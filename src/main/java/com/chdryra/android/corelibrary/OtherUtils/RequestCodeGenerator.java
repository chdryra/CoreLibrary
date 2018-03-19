/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.OtherUtils;

import java.util.Random;

/**
 * Created by: Rizwan Choudrey
 * On: 30/08/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class RequestCodeGenerator {
    private static final Random RAND = new Random();
    private static final int MAX_16_BIT = 65535;

    public static int getCode(String tag) {
        int code = tag.hashCode();
        code = code >= 0 ? code : -code;
        //TODO something more sophisticated
        if (code > MAX_16_BIT) code = RAND.nextInt(MAX_16_BIT);
        return code;
    }

    public static int getCode(Class<?> clazz) {
        return getCode(clazz.getName());
    }

    public static int getCode(Class<?> clazz, String tag) {
        return getCode(clazz.getName() + "." + tag);
    }
}
