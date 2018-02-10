/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Collections;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 27/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public interface CollectionIdable<Id, T extends CollectionIdable.Idable<Id>> extends Collection<T>{
    interface Idable<T> {
        T getId();
    }

    ArrayList<Id> getIds();

    @Nullable
    T get(Id name);
}
