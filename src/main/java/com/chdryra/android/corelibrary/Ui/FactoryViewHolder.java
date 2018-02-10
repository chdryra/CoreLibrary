/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Ui;

import android.view.View;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public interface FactoryViewHolder<DataType> {
    ViewHolderAbstract<DataType> newViewHolder(View v, int position);

    int getViewHolderLayout(int viewType);
}
