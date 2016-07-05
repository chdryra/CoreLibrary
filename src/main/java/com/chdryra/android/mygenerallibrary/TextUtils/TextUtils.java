/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.TextUtils;

import android.util.Patterns;
import android.widget.TextView;

import org.apache.commons.lang3.text.WordUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

/**
 * Created by: Rizwan Choudrey
 * On: 19/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class TextUtils {
    //Static methods
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

    public static boolean isTooLargeForTextView(TextView text, String newText) {
        float textWidth = text.getPaint().measureText(newText);
        return (textWidth >= text.getMeasuredWidth());
    }

    public static String commaDelimited(ArrayList<String> strings) {
        String ret = "";
        for (String string : strings) {
            ret += string + ",";
        }
        return ret.substring(0, ret.length() - 1);
    }

    public static ArrayList<String> splitString(String string, String delimiter) {
        return (ArrayList<String>) Arrays.asList(string.split(delimiter));
    }

    public static String toCamelCase(String string) {
        return WordUtils.capitalize(string).replaceAll(" ", "");
    }
}
