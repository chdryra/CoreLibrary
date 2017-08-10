/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Comparators;


/**
 * Created by: Rizwan Choudrey
 * On: 28/11/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface DataGetter<ObjectType, DataType> {
    DataType getData(ObjectType item);
}
