/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Collections;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 27/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public interface IdableCollection<Id, T extends IdableCollection.Idable<Id>> extends Collection<T>{
    interface Idable<T> {
        T getId();
    }

    ArrayList<Id> getIds();

    @Nullable
    T get(Id name);
}