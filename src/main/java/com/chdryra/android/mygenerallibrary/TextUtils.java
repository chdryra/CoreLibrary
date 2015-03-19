/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 19 March, 2015
 */

package com.chdryra.android.mygenerallibrary;

import android.util.Patterns;

import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * Created by: Rizwan Choudrey
 * On: 19/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class TextUtils {
    public static ArrayList<String> getLinks(String text) {
        ArrayList<String> links = new ArrayList<>();

        Matcher matcher = Patterns.WEB_URL.matcher(text);
        while (matcher.find()) links.add(matcher.group());
        return links;
    }

    public static String toShortenedString(URL url) {
        String protocol = url.getProtocol();
        String result = url.toExternalForm().replaceFirst(protocol + ":", "");
        if (result.startsWith("//")) {
            result = result.substring(2);
        }

        result = result.trim();
        if (result.endsWith("/")) {
            result = (String) result.subSequence(0, result.length() - 1);
        }

        return result.toLowerCase();
    }
}
