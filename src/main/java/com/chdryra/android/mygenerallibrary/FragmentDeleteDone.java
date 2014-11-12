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
 * Standard fragment for an activity with  "Delete", "Done" and "Up" icons in the action bar.
 * <p>
 * Shows a "Delete" (bin) and "Done" (tick) icon in the action bar. Also shows an "Up" icon by
 * default effectively allowing a "Cancel" operation. Manages delete confirmation if delete
 * pressed and there is data to delete. Also manages actions to perform on up/delete/done.
 * </p>
 */
public class FragmentDeleteDone extends Fragment {
    public static final int                MENU_ID        = R.menu.menu_delete_done;
    public static final int                MENU_UP_ID     = android.R.id.home;
    public static final int                MENU_DELETE_ID = R.id.menu_item_delete;
    public static final int                MENU_DONE_ID   = R.id.menu_item_done;
    public static final ActivityResultCode RESULT_UP      = ActivityResultCode.UP;
    public static final ActivityResultCode RESULT_DELETE  = ActivityResultCode.DELETE;
    public static final ActivityResultCode RESULT_DONE    = ActivityResultCode.DONE;

    private static final int DELETE_CONFIRM = 314;

    private boolean mDismissOnDone   = true;
    private boolean mDismissOnDelete = false;
    private String mDeleteWhat;
    private Intent mReturnData;
    private boolean mDisplayHomeAsUp = true;

    protected void setDisplayHomeAsUp(boolean displayHomeAsUp) {
        mDisplayHomeAsUp = displayHomeAsUp;
        setDisplayHomeAsUp();
    }

    private void setDisplayHomeAsUp() {
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(mDisplayHomeAsUp);
        }
    }

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
        inflater.inflate(MENU_ID, menu);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == MENU_UP_ID) {
            doUpSelected();
            return true;
        } else if (itemId == MENU_DELETE_ID && hasDataToDelete()) {
            showDeleteConfirmDialog();
            return true;
        } else if (itemId == MENU_DONE_ID) {
            doDoneSelected();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void doUpSelected() {
        onUpSelected();
        sendResult(RESULT_UP);
        getActivity().finish();
    }

    private void doDeleteSelected() {
        if (hasDataToDelete()) {
            onDeleteSelected();
            if (mDismissOnDelete) {
                sendResult(RESULT_DELETE);
                getActivity().finish();
            }
        }
    }

    private void doDoneSelected() {
        onDoneSelected();
        if (mDismissOnDone) {
            sendResult(RESULT_DONE);
            getActivity().finish();
        }
    }

    protected boolean hasDataToDelete() {
        return false;
    }

    protected void sendResult(ActivityResultCode resultCode) {
        getActivity().setResult(resultCode.get(), getCurrentReturnData());
    }

    protected Intent getCurrentReturnData() {
        if (mReturnData == null) {
            return getNewReturnData();
        } else {
            return mReturnData;
        }
    }

    protected Intent getNewReturnData() {
        mReturnData = new Intent();
        return mReturnData;
    }

    protected void setDeleteWhatTitle(String deleteWhat) {
        mDeleteWhat = deleteWhat;
    }

    protected void onDeleteSelected() {
    }

    protected void onDoneSelected() {
    }

    protected void onUpSelected() {
    }

    protected void setDismissOnDone(boolean dismiss) {
        mDismissOnDone = dismiss;
    }

    protected void dismissOnDelete() {
        mDismissOnDelete = true;
    }

    private void showDeleteConfirmDialog() {
        DialogDeleteConfirmFragment.showDeleteConfirmDialog(mDeleteWhat,
                FragmentDeleteDone.this,
                DELETE_CONFIRM, getFragmentManager());
    }
}
