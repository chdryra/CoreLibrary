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
 * "Done" (right button).
 * <p/>
 * <p>
 * By default will show a delete confirmation alert Dialog if Delete is pressed and
 * <code>hasDataToDelete()</code> returns true.
 * </p>
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

    /**
     * If there is data to delete and delete confirmation is required,
     * will show delete confirmation alert dialog otherwise calls the default implementation.
     */
    @Override
    protected void onMiddleButtonClick() {
        if (hasDataToDelete() && mDeleteConfirmation) {
            showDeleteConfirmDialog();
        } else {
            super.onMiddleButtonClick();
        }
    }

    /**
     * By default calls <code>onDeleteButtonClick()</code> if there is data to delete.
     *
     * @see #onDeleteButtonClick()
     */

    @Override
    protected void onActionButtonClick() {
        if (hasDataToDelete()) {
            onDeleteButtonClick();
        }
    }

    /**
     * Switch off delete confirmation when Delete is pressed.
     */
    protected void noDeleteConfirmation() {
        mDeleteConfirmation = false;
    }

    /**
     * Called when the "Delete" button is clicked (in addition to sending "Delete"
     * ActivityResultCode to the commissioning activity). By default does nothing.
     */
    protected void onDeleteButtonClick() {
    }

    /**
     * Delete mechanics only performed if this returns true.
     *
     * @return boolean: delete button behaviour only performed if this returns true.
     */
    protected boolean hasDataToDelete() {
        return false;
    }

    /**
     * Sets the delete confirmation alert dialog title as "Delete (deleteWhat)?". Ensures delete
     * confirmation alert dialog is shown if called.
     * @param deleteWhat
     */
    protected void setDeleteWhatTitle(String deleteWhat) {
        mDeleteWhat = deleteWhat;
        mDeleteConfirmation = true;
    }

    /**
     * Overrides super method: to capture result from delete confirmation alert dialog.
     * @param requestCode
     * @param resultCode
     * @param data
     */
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
