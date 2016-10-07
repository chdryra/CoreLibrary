/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.CacheUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chdryra.android.mygenerallibrary.OtherUtils.TagKeyGenerator;

import java.util.UUID;

/**
 * A way of passing objects back and forth between activities. There is a limit of 1MB for the
 * passing of parcelable objects between Intents so this allows just a key to be passed instead.
 */
public class ItemPacker<T> {
    private static final String ID = TagKeyGenerator.getKey(ItemPacker.class, "Id");
    private final ObjectHolder mObjects;

    public ItemPacker() {
        super();
        mObjects = new ObjectHolder();
    }

    public void pack(T item, Intent i) {
        i.putExtra(ID, pack(item));
    }

    public void pack(T item, Bundle args) {
        args.putString(ID, pack(item));
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public T unpack(Intent i) {
        return (T) mObjects.removeObject(i.getStringExtra(ID));
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public T unpack(Bundle args) {
        String string = args.getString(ID);
        return string != null ? (T) mObjects.removeObject(string) : null;
    }

    private String pack(T item) {
        String id = UUID.randomUUID().toString();
        mObjects.addObject(id, item);
        return id;
    }
}
