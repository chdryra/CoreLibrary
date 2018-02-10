/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Viewholder;

/**
 * Created by: Rizwan Choudrey
 * On: 13/09/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface VhQueryFilter<T extends ViewHolderData> {
    VhDataList<T> filter(String query);
}
