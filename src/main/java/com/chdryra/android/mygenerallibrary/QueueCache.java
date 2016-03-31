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
import java.util.Queue;

/**
 * Created by: Rizwan Choudrey
 * On: 13/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class QueueCache<T> {
    private Cache<T> mCache;
    private Queue<String> mIds;
    private int mCacheMax;

    public interface Cache<T> {
        void put(String id, T item);

        T get(String id);

        T remove(String id);
    }

    public QueueCache(Cache<T> cache, int cacheMax) {
        mCacheMax = cacheMax;
        mIds = new LinkedList<>();
        mCache = cache;
    }

    @Nullable
    public T add(String id, T item) {
        if (containsId(id)) return swap(id, item);

        addToCache(id, item);

        return maxSizeBreached() ? removeHeadOfQueue() : null;
    }

    public T get(String id) {
        return mCache.get(id);
    }

    public T remove(String id) {
        return removeFromCache(id);
    }

    public boolean containsId(String id) {
        return mIds.contains(id);
    }

    private boolean maxSizeBreached() {
        return mIds.size() > mCacheMax;
    }

    private T removeHeadOfQueue() {
        return removeFromCache(mIds.remove());
    }

    @Nullable
    private T swap(String id, T item) {
        T old = removeFromCache(id);
        addToCache(id, item);
        return old.equals(item) ? null : old;
    }

    private T removeFromCache(String id) {
        mIds.remove(id);
        return mCache.remove(id);
    }

    private void addToCache(String id, T item) {
        mIds.add(id);
        mCache.put(id, item);
    }
}
