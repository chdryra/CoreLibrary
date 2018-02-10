/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.LocationServices;

import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 14/01/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface AutoCompleter<T> extends QueryFilter<T> {
    void disconnectFromProvider();

    @Override
    List<T> filter(String query);
}
