/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.AsyncUtils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Rizwan Choudrey
 * On: 01/04/2016
 * Email: rizwan.choudrey@gmail.com
 */
public abstract class QueueConsumer<T> implements AsyncWorkQueue.QueueObserver,
        WorkStoreCallback<T> {
    private AsyncWorkQueue<T> mQueue;
    private Map<String, ItemWorker<T>> mWorkers;
    private ArrayList<String> mWorking;
    private Map<String, WorkerToken> mTokens;

    public interface ItemWorker<T> {
        void doWork(T item);
    }

    protected abstract void OnFailedToRetrieve(String requestId, CallbackMessage result);

    protected abstract ItemWorker<T> newWorker(String itemId);

    public QueueConsumer() {
        mWorkers = new HashMap<>();
        mWorking = new ArrayList<>();
        mTokens = new HashMap<>();
    }

    public void setQueue(AsyncWorkQueue<T> queue) {
        if (mQueue != null) throw new IllegalStateException("Cannot reset Queue!");

        mQueue = queue;
        mQueue.registerObserver(this);
    }

    protected void onWorkCompleted(String itemId) {
        mWorking.remove(itemId);
        onWorkerRemoved(mWorkers.remove(itemId));
        mQueue.workComplete(mTokens.get(itemId));
    }

    protected void onWorkerRemoved(ItemWorker<T> remove) {

    }

    @Override
    public void onAddedToQueue(String itemId) {
        mTokens.put(itemId, mQueue.getItemForWork(itemId, this, this));
    }

    @Override
    public void onAddedToStore(T item, String storeId, CallbackMessage result) {

    }

    @Override
    public void onRetrievedFromStore(T item, String requestedId, CallbackMessage result) {
        if (!mWorking.contains(requestedId)) {
            mWorking.add(requestedId);
            getWorker(requestedId).doWork(item);
        }
    }

    @Override
    public void onRemovedFromStore(String itemId, CallbackMessage result) {

    }

    @Override
    public void onFailed(@Nullable T item, @Nullable String itemId, CallbackMessage result) {
        if (itemId != null) OnFailedToRetrieve(itemId, result);
    }

    @NonNull
    private ItemWorker<T> getWorker(String itemId) {
        if (!mWorkers.containsKey(itemId)) {
            ItemWorker<T> uploader = newWorker(itemId);
            mWorkers.put(itemId, uploader);
        }

        return mWorkers.get(itemId);
    }
}
