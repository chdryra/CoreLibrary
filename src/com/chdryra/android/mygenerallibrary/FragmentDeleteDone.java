/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * Standardised fragment version of DialogCancelDeleteDone.
 * <p>
 * Shows a "Delete" (bin) and "Done" (tick) icon in the action bar. Also shows an "Up" icon by
 * default effectively allowing a "Cancel" operation. Manages delete confirmation if delete
 * pressed and there is data to delete. Also manages actions to perform on up/delete/done.
 * </p>
 *
 * @see DialogCancelDeleteDoneFragment
 */
public class FragmentDeleteDone extends Fragment {
    private static final int DELETE_CONFIRM = 0;

    private boolean mDismissOnDone   = true;
    private boolean mDismissOnDelete = false;
    private String mDeleteWhat;
    private Intent mReturnData;
    private boolean mDisplayHomeAsUp = true;

    /**
     * Can define whether "Up" is shown on the action bar.
     * @param displayHomeAsUp
     */
    protected void setDisplayHomeAsUp(boolean displayHomeAsUp) {
        mDisplayHomeAsUp = displayHomeAsUp;
        setDisplayHomeAsUp();
    }

    private void setDisplayHomeAsUp() {
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(mDisplayHomeAsUp);
        }
    }

    /**
     * Overrides super method: manages the delete confirmation callback.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DELETE_CONFIRM:
                if (DialogDeleteConfirmFragment.DELETE_CONFIRM.getResultCode().equals(resultCode)) {
                    doDeleteSelected();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setDisplayHomeAsUp();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, android.view.MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_delete_done, menu);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            doUpSelected();
            return true;
        } else if (itemId == R.id.menu_item_delete) {
            if (hasDataToDelete()) {
                showDeleteConfirmDialog();
            } else {
                doDeleteSelected();
            }
            return true;
        } else if (itemId == R.id.menu_item_done) {
            doDoneSelected();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void doDeleteSelected() {
        if (hasDataToDelete()) {
            onDeleteSelected();
            sendResult(ActivityResultCode.DELETE);
            if (mDismissOnDelete) {
                getActivity().finish();
            }
        }
    }

    /**
     * Delete mechanics only performed if this returns true.
     * @return boolean: delete icon behaviour only performed if this returns true.
     */
    protected boolean hasDataToDelete() {
        return false;
    }

    /**
     * Sends the ActivityResultCode to the commissioning activity.
     */
    protected void sendResult(ActivityResultCode resultCode) {
        getActivity().setResult(resultCode.get(), getReturnData());
    }

    /**
     * Gets the currently held Intent data that will be returned to the commissioning activity if
     * an icon is pressed.
     * @return Intent: holds the return data.
     */
    protected Intent getReturnData() {
        if (mReturnData == null) {
            return getNewReturnData();
        } else {
            return mReturnData;
        }
    }

    /**
     * Initialises and returns new Intent data that will be passed to the commissioning activity
     * if an icon is pressed.
     * @return Intent: holds the return data.
     */
    protected Intent getNewReturnData() {
        mReturnData = new Intent();
        return mReturnData;
    }

    /**
     * Sets the delete confirmation alert dialog title as "Delete (deleteWhat)?".
     * @param deleteWhat
     */
    protected void setDeleteWhatTitle(String deleteWhat) {
        mDeleteWhat = deleteWhat;
    }

    /**
     * Called when the "Delete" icon is clicked (in addition to sending "Delete"
     * ActivityResultCode to the commissioning activity). By default does nothing.
     */
    protected void onDeleteSelected() {
    }

    /**
     * Called when the "Done" icon is clicked (in addition to sending "Done"
     * ActivityResultCode to the commissioning activity). By default does nothing.
     */
    protected void onDoneSelected() {
    }

    /**
     * Called when the "Up" icon is clicked (in addition to sending "Cancel"
     * ActivityResultCode to the commissioning activity). By default does nothing.
     */
    protected void onUpSelected() {
    }

    /**
     * Set whether to close the activity if Done is pressed.
     * @param dismiss
     */
    protected void setDismissOnDone(boolean dismiss) {
        mDismissOnDone = dismiss;
    }

    /**
     * Set whether to close the activity if Delete is pressed.
     * @param dismiss
     */
    protected void setDismissOnDelete(boolean dismiss) {
        mDismissOnDelete = dismiss;
    }

    private void showDeleteConfirmDialog() {
        DialogDeleteConfirmFragment.showDeleteConfirmDialog(mDeleteWhat,
                FragmentDeleteDone.this, DELETE_CONFIRM, getFragmentManager());
    }

    private void doDoneSelected() {
        onDoneSelected();
        sendResult(ActivityResultCode.DONE);
        if (mDismissOnDone) {
            getActivity().finish();
        }
    }

    private void doUpSelected() {
        onUpSelected();
        sendResult(ActivityResultCode.UP);
        getActivity().finish();
    }
}
