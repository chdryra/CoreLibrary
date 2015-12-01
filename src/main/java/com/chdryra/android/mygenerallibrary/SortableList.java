package com.chdryra.android.mygenerallibrary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * Created by: Rizwan Choudrey
 * On: 23/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public interface SortableList<T> extends Collection<T> {
    String NO_ELEMENT = "No more elements left";
    String ILLEGAL_STATE = "Have to do at least one next() before you " +
            "can delete";

    T getItem(int position);

    void sort();

    void sort(Comparator<? super T> comparator);

    ArrayList<T> toArrayList();
}
