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
    public T unpack(Intent i) {
        return getObject(i.getStringExtra(ID));
    }

    @Nullable
    public T unpack(Bundle args) {
        return getObject(args.getString(ID));
    }

    @Nullable
    @SuppressWarnings("unchecked")
    private T getObject(@Nullable String objectId) {
        return objectId != null ? (T) mObjects.removeObject(objectId) : null;
    }

    private String pack(T item) {
        String id = UUID.randomUUID().toString();
        mObjects.addObject(id, item);
        return id;
    }
}
