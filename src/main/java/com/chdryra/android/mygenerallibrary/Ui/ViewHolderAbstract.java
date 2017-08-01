/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public abstract class ViewHolderAbstract<DataType> extends RecyclerView.ViewHolder {
    public abstract void updateData(DataType data);

    public ViewHolderAbstract(View itemView) {
        super(itemView);
    }
}
