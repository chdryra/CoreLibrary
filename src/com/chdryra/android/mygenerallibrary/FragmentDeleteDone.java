/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDeleteDone extends DialogFragment {
    private static final int DELETE_CONFIRM = 0;

    private boolean mDismissOnDone   = true;
    private boolean mDismissOnDelete = false;
    private String mDeleteWhat;
    private Intent mReturnData;
    private boolean mDisplayHomeAsUp = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    protected void setDisplayHomeAsUp(boolean displayHomeAsUp) {
        mDisplayHomeAsUp = displayHomeAsUp;
        setDisplayHomeAsUp();
    }

    protected void setDisplayHomeAsUp() {
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

    protected boolean hasDataToDelete() {
        return false;
    }

    protected void onDeleteSelected() {
    }

    protected void sendResult(ActivityResultCode resultCode) {
        getActivity().setResult(resultCode.get(), getReturnData());
    }

    protected Intent getReturnData() {
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

    protected void onDoneSelected() {
    }

    protected void onUpSelected() {
    }

    protected void setDismissOnDone(boolean dismiss) {
        mDismissOnDone = dismiss;
    }

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
