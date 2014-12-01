/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Standard 3-button Dialog for "Cancel" (left button), "Delete" (middle button),
 * "Done" (right button). By default will show a delete confirmation dialog
 * {@link com.chdryra.android.mygenerallibrary.DialogDeleteConfirmFragment} if "Delete" is pressed
 * and {@link #hasDataToDelete()} returns true.
 */
public abstract class DialogCancelDeleteDoneFragment extends DialogCancelActionDoneFragment {
    public static final ActionType DELETE_ACTION               = ActionType.DELETE;
    public static final int        DELETE_CONFIRM_REQUEST_CODE = 0;

    private String mDeleteWhat;

    @Override
    protected abstract View createDialogUI();

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DELETE_CONFIRM_REQUEST_CODE) {
            if (DialogDeleteConfirmFragment.DELETE_CONFIRM.getResultCode().equals(resultCode)) {
                super.onMiddleButtonClick();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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
        DialogDeleteConfirmFragment.showDeleteConfirmDialog(mDeleteWhat,
                DialogCancelDeleteDoneFragment.this, DELETE_CONFIRM_REQUEST_CODE,
                getFragmentManager());
    }
}
