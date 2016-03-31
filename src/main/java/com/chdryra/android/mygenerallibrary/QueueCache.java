/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary;

import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by: Rizwan Choudrey
 * On: 13/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class QueueCache<T> {
    private Map<String, T> mCache;
    private Queue<String> mIds;
    private int mCacheMax;

    public QueueCache(int cacheMax) {
        mCacheMax = cacheMax;
        mIds = new LinkedList<>();
    }

    @Nullable
    public T add(String id, T item) {
        if(mIds.contains(id)) return swap(id, item);

        mIds.add(id);
        mCache.put(id, item);

        return mIds.size() > mCacheMax ? mCache.remove(mIds.remove()) : null;
    }

    private T swap(String id, T item) {
        T old = mCache.remove(id);
        mCache.put(id, item);
        return old;
    }

    @Nullable
    public T remove(String id) {
        if(mIds.contains(id)) {
            mIds.remove(id);
            return mCache.remove(id);
        }

        return null;
    }
}
