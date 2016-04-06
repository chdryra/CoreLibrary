/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.CacheUtils;

import java.util.Hashtable;

/**
 * A way of passing objects back and forth between activities. There is a limit of 1MB for the
 * passing of parcelable objects between Intents so this allows just a key to be passed instead.
 */
public class ObjectHolder {
    private final Hashtable<String, Object> mData;

    //Constructors
    public ObjectHolder() {
        mData = new Hashtable<>();
    }

    public boolean addObject(String key, Object object) {
        if (!mData.containsKey(key)) {
            mData.put(key, object);
            return true;
        }

        return false;
    }

    public Object getObject(String key) {
        return mData.get(key);
    }

    public void removeObject(String key) {
        mData.remove(key);
    }
}
