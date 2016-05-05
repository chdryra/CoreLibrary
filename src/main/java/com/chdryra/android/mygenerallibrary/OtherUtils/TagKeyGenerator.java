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
public class TagKeyGenerator {
    public static String getTag(Class<?> clazz) {
        return clazz.getName();
    }

    public static String getTag(Class<?> clazz, String tag) {
        return clazz.getName() + "." + tag;
    }

    public static String getKey(Class<?> clazz, String key) {
        return clazz.getPackage().getName() + "." +  clazz.getName() + "." + key;
    }
}
