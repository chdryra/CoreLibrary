/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Collections;



import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created by: Rizwan Choudrey
 * On: 03/09/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class CollectionIdableImpl<Id, T extends CollectionIdable.Idable<Id>> extends AbstractCollection<T> implements CollectionIdable<Id, T> {
    private final LinkedHashSet<T> mItems;

    public CollectionIdableImpl() {
        mItems = new LinkedHashSet<>();
    }

    public CollectionIdableImpl(T defaultItem) {
        mItems = new LinkedHashSet<>();
        add(defaultItem);
    }

    public CollectionIdableImpl(Collection<T> items) {
        mItems = new LinkedHashSet<>();
        addAll(items);
    }

    @Override
    public int size() {
        return mItems.size();
    }

    @Override
    public ArrayList<Id> getIds() {
        ArrayList<Id> names = new ArrayList<>();
        for(T item : this) {
            names.add(item.getId());
        }

        return names;
    }

    @Override
    public T get(Id id) {
        for(T item : mItems) {
            if(item.getId().equals(id)) return item;
        }

        return null;
    }

    @Override
    public boolean add(T comparator) {
        return mItems.add(comparator);
    }

    @Override
    public Iterator<T> iterator() {
        return mItems.iterator();
    }
}
