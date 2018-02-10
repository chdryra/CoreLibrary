/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A simple sortable list that can be iterated over.
 *
 * @param <T>: type of the items.
 */
public class SortableListImpl<T> extends ArrayList<T> implements SortableList<T>{
    private boolean mIsSorted = false;


    @Override
    public boolean add(T item) {
        mIsSorted = false;
        return super.add(item);
    }

    @Override
    public boolean remove(Object item) {
        mIsSorted = false;
        return super.remove(item);
    }

    @Override
    public void sort() {
        sort(getDefaultComparator());
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        Object[] a = toArray();
        Arrays.sort(a, (Comparator) comparator);
        ListIterator<T> i = listIterator();
        //TODO make type safe
        for (int j = 0; j < a.length; j++) {
            i.next();
            i.set((T) a[j]);
        }
        mIsSorted = true;
    }

    @Override
    public void setUnsorted() {
        mIsSorted = false;
    }

    @Override
    public boolean isSorted() {
        return mIsSorted;
    }

    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayList = new ArrayList<>();
        arrayList.addAll(this);
        return arrayList;
    }

    //protected methods
    protected Comparator<? super T> getDefaultComparator() {
        return new Comparator<T>() {
            //Overridden
            @Override
            public int compare(T lhs, T rhs) {
                if (contains(lhs) && contains(rhs)) {
                    return indexOf(lhs) - indexOf(rhs);
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
            return mPosition < size() && get(mPosition) != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return get(mPosition++);
            } else {
                throw new NoSuchElementException(NO_ELEMENT);
            }
        }

        @Override
        public void remove() {
            if (mPosition > 0) {
                SortableListImpl.this.remove(get(--mPosition));
                mIsSorted = false;
            } else {
                throw new IllegalStateException(ILLEGAL_STATE);
            }
        }
    }
}
