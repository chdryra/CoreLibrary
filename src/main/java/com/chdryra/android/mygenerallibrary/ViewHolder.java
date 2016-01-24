/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * For the ViewHolder pattern used by ListView-type Adapters which utilise item views from a
 * small, reuseable pool. Needs to be initially inflated, then updated when reused.
 *
 * @see <a href="http://developer.android.com/training/improving-layouts/smooth-scrolling
 * .html#ViewHolder">ViewHolder pattern</a>
 * @see ViewHolderAdapter
 */
public interface ViewHolder {
    //abstract
    public void inflate(Context context, ViewGroup parent);

    public void updateView(ViewHolderData data);

    public View getView();
}
