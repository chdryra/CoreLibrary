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
 * Standard 3-button Dialog for "Cancel" (left button), "Delete" (middle button),
 * "Done" (right button). By default will show a delete confirmation dialog
 * {@link DialogDeleteConfirm} if "Delete" is pressed
 * and {@link #hasDataToDelete()} returns true.
 */
public abstract class DialogCancelDeleteDoneFragment extends DialogCancelActionDoneFragment
        implements DialogAlertFragment.DialogAlertListener {
    public static final ActionType DELETE_ACTION  = ActionType.DELETE;
    public static final int        DELETE_CONFIRM = 0;

    private String mDeleteWhat;

    @Override
    protected abstract View createDialogUi();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionButtonAction(DELETE_ACTION);
        dismissDialogOnActionClick();
    }

    @Override
    protected final void onMiddleButtonClick() {
        if (hasDataToDelete()) {
            showDeleteConfirmDialog();
        } else {
            super.onMiddleButtonClick();
        }
    }

    @Override
    protected final void onActionButtonClick() {
        if (hasDataToDelete()) {
            onConfirmedDeleteButtonClick();
        }
    }

    public void setDeleteWhatTitle(String deleteWhat) {
        mDeleteWhat = deleteWhat;
    }

    @Override
    public void onAlertNegative(int requestCode, Bundle args) {

    }

    @Override
    public void onAlertPositive(int requestCode, Bundle args) {
        if (requestCode == DELETE_CONFIRM) super.onMiddleButtonClick();
    }

    public void clickDeleteButton() {
        clickActionButton();
    }

    protected void onConfirmedDeleteButtonClick() {
    }

    protected boolean hasDataToDelete() {
        return false;
    }

    private void showDeleteConfirmDialog() {
        DialogDeleteConfirm.showDialog(mDeleteWhat, DialogCancelDeleteDoneFragment.this,
                DELETE_CONFIRM, getFragmentManager());
    }
}
