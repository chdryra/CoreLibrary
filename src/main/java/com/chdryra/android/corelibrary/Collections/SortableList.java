/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 23/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public interface SortableList<T> extends List<T> {
    String NO_ELEMENT = "No more elements left";
    String ILLEGAL_STATE = "Have to do at least one next() before you " +
            "can delete";

    void sort();

    void sort(Comparator<? super T> comparator);

    void setUnsorted();

    boolean isSorted();

    ArrayList<T> toArrayList();
}
