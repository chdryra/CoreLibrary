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
import android.view.ViewGroup;

/**
 * Standardised 3-button Dialog for "Cancel" (left button), "Delete" (middle button),
 * "Done" (right button). By default will show a delete confirmation alert Dialog if Delete is
 * pressed and <code>hasDataToDelete()</code> returns true.
 */
public abstract class DialogCancelDeleteDoneFragment extends DialogCancelActionDoneFragment {
    private static final int DELETE_CONFIRM = 0;

    private boolean mDeleteConfirmation = true;
    private String mDeleteWhat;

    protected abstract View createDialogUI(ViewGroup parent);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionButtonAction(ActionType.DELETE);
        dismissDialogOnActionClick();
    }

    @Override
    protected final void onMiddleButtonClick() {
        if (hasDataToDelete() && mDeleteConfirmation) {
            showDeleteConfirmDialog();
        } else {
            super.onMiddleButtonClick();
        }
    }

    @Override
    protected final void onActionButtonClick() {
        if (hasDataToDelete()) {
            onDeleteButtonClick();
        }
    }

    protected void noDeleteConfirmation() {
        mDeleteConfirmation = false;
    }

    protected void onDeleteButtonClick() {
    }

    protected boolean hasDataToDelete() {
        return false;
    }

    protected void setDeleteWhatTitle(String deleteWhat) {
        mDeleteWhat = deleteWhat;
        mDeleteConfirmation = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DELETE_CONFIRM:
                if (DialogDeleteConfirmFragment.DELETE_CONFIRM.getResultCode().equals(resultCode)) {
                    super.onMiddleButtonClick();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private void showDeleteConfirmDialog() {
        DialogDeleteConfirmFragment.showDeleteConfirmDialog(mDeleteWhat,
                DialogCancelDeleteDoneFragment.this, DELETE_CONFIRM, getFragmentManager());
    }
}
