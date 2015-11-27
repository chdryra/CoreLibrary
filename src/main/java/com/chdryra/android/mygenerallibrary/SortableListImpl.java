/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple sortable list that can be iterated over.
 *
 * @param <T>: type of the items.
 */
public class SortableListImpl<T> implements SortableList<T> {
    protected ArrayList<T> mData = new ArrayList<>();

    @Override
    public void add(T item) {
        mData.add(item);
    }

    @Override
    public boolean contains(T item) {
        return mData.contains(item);
    }

    @Override
    public void remove(T item) {
        mData.remove(item);
    }

    @Override
    public void removeAll() {
        mData.clear();
    }

    @Override
    public int size() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public void sort() {
        sort(getDefaultComparator());
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        Collections.sort(mData, comparator);
    }

    @Override
    public void addList(Iterable<T> list) {
        for (T item : list) {
            add(item);
        }
    }

    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayList = new ArrayList<>();
        arrayList.addAll(mData);
        return arrayList;
    }

    //protected methods
    protected Comparator<? super T> getDefaultComparator() {
        return new Comparator<T>() {
            //Overridden
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

    //Overridden
    @Override
    public Iterator<T> iterator() {
        return new SortableListIterator();
    }

    public class SortableListIterator implements Iterator<T> {
        int mPosition = 0;

        private SortableListIterator() {

        }

        //Overridden
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
                mData.remove((getItem(--mPosition)));
            } else {
                throw new IllegalStateException(ILLEGAL_STATE);
            }
        }
    }
}
