/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * A simple sortable list that can be iterated over.
 *
 * @param <T>: type of the items.
 */
public abstract class SortableList<T> implements Iterable<T> {
    protected final LinkedList<T> mData     = new LinkedList<T>();
    private         boolean       mIsSorted = false;

    public SortableList() {

    }

    public void add(T item) {
        mData.add(item);
        mIsSorted = false;
    }

    public void add(SortableList<T> list) {
        for (T item : list) {
            mData.add(item);
        }
        mIsSorted = false;
    }

    public void remove(T item) {
        mData.remove(item);
        mIsSorted = false;
    }

    public void removeAll() {
        mData.clear();
    }

    public boolean contains(T item) {
        return mData.contains(item);
    }

    public int indexOf(T item) {
        return mData.indexOf(item);
    }

    public int size() {
        return mData.size();
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    public void sort() {
        sort(getDefaultComparator());
    }

    public void sort(Comparator<T> comparator) {
        if (!isSorted()) {
            Collections.sort(mData, comparator);
        }
        mIsSorted = true;
    }

    protected Comparator<T> getDefaultComparator() {
        return new Comparator<T>() {
            @Override
            public int compare(T lhs, T rhs) {
                if (mData.contains(lhs) && mData.contains(rhs)) {
                    return mData.indexOf(lhs) - mData.indexOf(rhs);
                } else {
                    return 0;
                }
            }
        };
    }

    public boolean isSorted() {
        return mIsSorted;
    }

    @Override
    public Iterator<T> iterator() {
        return new SLIterator();
    }

    class SLIterator implements Iterator<T> {
        int position = 0;

        @Override
        public boolean hasNext() {
            return position < size() && getItem(position) != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return getItem(position++);
            } else {
                throw new NoSuchElementException("No more elements left");
            }
        }

        @Override
        public void remove() {
            if (position <= 0) {
                throw new IllegalStateException("Have to do at least one next() before you can " +
                        "delete");
            } else {
                mData.remove((getItem(position - 1)));
            }
        }
    }
}
