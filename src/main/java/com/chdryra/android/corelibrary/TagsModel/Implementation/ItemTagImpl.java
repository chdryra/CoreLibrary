/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.TagsModel.Implementation;

import android.support.annotation.NonNull;

import com.chdryra.android.corelibrary.TagsModel.Interfaces.ItemTag;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 10/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class ItemTagImpl implements ItemTag {
    private final ArrayList<String> mItemIds;
    private final String mTag;

    public ItemTagImpl(String tag, String id) {
        mTag = WordUtils.capitalize(tag);
        mItemIds = new ArrayList<>();
        mItemIds.add(id);
    }

    public void addItemId(String id) {
        if (!mItemIds.contains(id)) mItemIds.add(id);
    }

    public boolean removeItemId(String id) {
        mItemIds.remove(id);
        return getNumberTagged() == 0;
    }

    @Override
    public ArrayList<String> getItemIds() {
        return new ArrayList<>(mItemIds);
    }

    @Override
    public int getNumberTagged() {
        return mItemIds.size();
    }

    @Override
    public String getTag() {
        return mTag;
    }

    @Override
    public boolean tagsItem(String id) {
        return mItemIds.contains(id);
    }

    @Override
    public boolean isTag(String tag) {
        return mTag.equalsIgnoreCase(tag);
    }

    @Override
    public int compareTo(@NonNull ItemTag another) {
        return mTag.compareToIgnoreCase(another.getTag());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemTag)) return false;

        ItemTag itemTag = (ItemTag) o;

        if (!mItemIds.equals(itemTag.getItemIds())) return false;
        return isTag(itemTag.getTag());
    }

    @Override
    public int hashCode() {
        int result = mItemIds.hashCode();
        result = 31 * result + mTag.hashCode();
        return result;
    }
}
