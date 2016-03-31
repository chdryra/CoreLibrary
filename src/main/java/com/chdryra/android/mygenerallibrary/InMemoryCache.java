/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Rizwan Choudrey
 * On: 31/03/2016
 * Email: rizwan.choudrey@gmail.com
 */
public class InMemoryCache<T> implements QueueCache.Cache<T>{
    private Map<String, T> mMap;

    public InMemoryCache() {
        mMap = new HashMap<>();
    }

    @Override
    public void put(String id, T item) {
        mMap.put(id, item);
    }

    @Override
    public T get(String id) {
        return mMap.get(id);
    }

    @Override
    public T remove(String id) {
        return mMap.remove(id);
    }
}
