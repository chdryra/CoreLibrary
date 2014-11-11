/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
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
    public void inflate(Activity activity, ViewGroup parent);

    public void updateView(ViewHolderData data);

    public View getView();
}
