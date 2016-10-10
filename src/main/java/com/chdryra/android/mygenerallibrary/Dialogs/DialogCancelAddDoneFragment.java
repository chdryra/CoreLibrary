/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Dialogs;


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

    @Override
    protected final void onActionButtonClick() {
        onAddButtonClick();
    }

    @Override
    protected abstract View createDialogUi();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionButtonAction(ADD_ACTION);
        performActionOnDone();
    }
}
