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

/**
 * Standard 3-button Dialog for "Cancel" (left button), "Add" (middle button),
 * "Done" (right button).
 */
public abstract class DialogCancelAddDoneFragment extends DialogCancelActionDoneFragment {
    public static final ActionType ADD_ACTION = ActionType.ADD;

    public void clickAddButton() {
        clickActionButton();
    }

    protected void onAddButtonClick() {
    }

//Overridden
    @Override
    protected final void onActionButtonClick() {
        onAddButtonClick();
    }

    protected abstract View createDialogUi();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionButtonAction(ADD_ACTION);
        performActionOnDone();
    }
}
