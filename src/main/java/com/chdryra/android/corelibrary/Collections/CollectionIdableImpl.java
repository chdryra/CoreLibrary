/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Collections;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by: Rizwan Choudrey
 * On: 03/09/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class CollectionIdableImpl<Id, T extends CollectionIdable.Idable<Id>> extends
        LinkedHashSet<T> implements CollectionIdable<Id, T> {

    public CollectionIdableImpl() {
        super();
    }

    public CollectionIdableImpl(T defaultItem) {
        super();
        add(defaultItem);
    }

    public CollectionIdableImpl(Collection<T> items) {
        super(items);
    }

    @Override
    public ArrayList<Id> getIds() {
        ArrayList<Id> names = new ArrayList<>();
        for (T item : this) {
            names.add(item.getId());
        }

        return names;
    }

    @Override
    public T get(Id id) {
        for (T item : this) {
            if (item.getId().equals(id)) return item;
        }

        return null;
    }
}
