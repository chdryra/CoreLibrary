/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * Standard 3-button Dialog for "Cancel" (left button), "Add" (middle button),
 * "Done" (right button).
 */
public abstract class DialogCancelAddDoneFragment extends DialogCancelActionDoneFragment {

    protected abstract View createDialogUI(ViewGroup parent);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionButtonAction(ActionType.ADD);
        performActionOnDone();
    }

    @Override
    protected final void onActionButtonClick() {
        onAddButtonClick();
    }

    protected void onAddButtonClick() {
    }
}
