/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import java.net.URL;
import java.util.StringTokenizer;

public class RandomTextUtils {
	
	public static String toStringURL(URL url) {
		return url != null? url.toExternalForm() : null;
	}
	
	public static String toShortenedStringURL(URL url) {
		String urlString = toStringURL(url);
	    String protocol = url.getProtocol();
        String result = urlString.replaceFirst(protocol + ":", "");
        if (result.startsWith("//"))
            result = result.substring(2);
        
        result = result.trim();
        if(result.endsWith("/"))
        	result = (String)result.subSequence(0, result.length() - 1);
        
        return result;
	}
	
	public static String shortened(String string, String delimiters) {
		if(string != null) {
			StringTokenizer tokens = new StringTokenizer(string, delimiters);
			String shortened = tokens.nextToken();
			return shortened != null? shortened.trim() : null;
		} else
			return null;
	}
}
