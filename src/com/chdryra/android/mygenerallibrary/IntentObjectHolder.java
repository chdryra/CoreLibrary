/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import java.util.Hashtable;

public class IntentObjectHolder {
	 private final Hashtable<String, Object> mData;

	    public IntentObjectHolder() {
	        mData = new Hashtable<String, Object>();
	    }

	    public void addObject(String key, Object object) {
            if(!mData.containsKey(key))
	            mData.put(key, object);
	    }

	    public Object getObject(String key) {
	        return mData.get(key);
	    }

        public void removeObject(String key) { mData.remove(key);}
}
