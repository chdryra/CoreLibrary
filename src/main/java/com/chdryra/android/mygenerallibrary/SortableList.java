package com.chdryra.android.mygenerallibrary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by: Rizwan Choudrey
 * On: 23/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public interface SortableList<T> extends Iterable<T> {
    String NO_ELEMENT = "No more elements left";
    String ILLEGAL_STATE = "Have to do at least one next() before you " +
            "can delete";

    //Overridden
    @Override
    Iterator<T> iterator();

    void add(T item);

    boolean contains(T item);

    void remove(T item);

    void removeAll();

    int size();

    T getItem(int position);

    void sort();

    void sort(Comparator<? super T> comparator);

    void addList(Iterable<T> list);

    ArrayList<T> toArrayList();
}
