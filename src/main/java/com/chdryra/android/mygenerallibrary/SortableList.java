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
public class SortableList<T> implements Iterable<T> {
    public static final String        NO_ELEMENT    = "No more elements left";
    public static final String        ILLEGAL_STATE = "Have to do at least one next() before you " +
            "can " +
            "delete";
    protected final     LinkedList<T> mData         = new LinkedList<T>();

    public void add(T item) {
        mData.add(item);
    }

    public boolean contains(T item) {
        return mData.contains(item);
    }

    public void remove(T item) {
        mData.remove(item);
    }

    public void removeAll() {
        mData.clear();
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
        Collections.sort(mData, comparator);
    }

    @Override
    public Iterator<T> iterator() {
        return new SortableListIterator();
    }

    protected void add(SortableList<T> list) {
        for (T item : list) {
            add(item);
        }
    }

    protected Comparator<T> getDefaultComparator() {
        return new Comparator<T>() {
            @Override
            public int compare(T lhs, T rhs) {
                if (contains(lhs) && contains(rhs)) {
                    return mData.indexOf(lhs) - mData.indexOf(rhs);
                } else {
                    return 0;
                }
            }
        };
    }

    public class SortableListIterator implements Iterator<T> {
        int mPosition = 0;

        @Override
        public boolean hasNext() {
            return mPosition < size() && getItem(mPosition) != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return getItem(mPosition++);
            } else {
                throw new NoSuchElementException(NO_ELEMENT);
            }
        }

        @Override
        public void remove() {
            if (mPosition > 0) {
                mData.remove((getItem(mPosition - 1)));
            } else {
                throw new IllegalStateException(ILLEGAL_STATE);
            }
        }
    }
}
