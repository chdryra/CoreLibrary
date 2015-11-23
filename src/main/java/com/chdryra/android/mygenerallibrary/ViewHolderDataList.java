/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * An iterable, sortable collection of {@link ViewHolderData} objects that facilitate the
 * {@link ViewHolder}
 * pattern.
 *
 * @param <T>: a type that implements {@link ViewHolderData}.
 */
public interface ViewHolderDataList<T extends ViewHolderData> extends SortableList<T> {
    @Override
    Iterator<T> iterator();

    @Override
    void add(T item);

    @Override
    boolean contains(T item);

    @Override
    void remove(T item);

    @Override
    void removeAll();

    @Override
    int size();

    @Override
    T getItem(int position);

    @Override
    void sort();

    @Override
    void sort(Comparator<T> comparator);

    @Override
    void addList(Iterable<T> list);

    @Override
    ArrayList<T> toArrayList();
}
