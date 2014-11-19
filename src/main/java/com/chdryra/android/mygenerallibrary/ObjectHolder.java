/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import java.util.Hashtable;

/**
 * A way of passing objects back and forth between activities. There is a limit of 1MB for the
 * passing of parcelable objects between Intents so this allows just a key to be passed instead.
 */
public class ObjectHolder {
    private final Hashtable<String, Object> mData;

    public ObjectHolder() {
        mData = new Hashtable<String, Object>();
    }

    public void addObject(String key, Object object) {
        if (!mData.containsKey(key)) {
            mData.put(key, object);
        }
    }

    public Object getObject(String key) {
        return mData.get(key);
    }

    public void removeObject(String key) {
        mData.remove(key);
    }
}
