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

public abstract class DialogCancelDeleteDoneFragment extends DialogCancelActionDoneFragment {
	private static final int DELETE_CONFIRM = 0;

	private boolean mDeleteConfirmation = true;
	private String mDeleteWhat;
	
	protected abstract View createDialogUI(); 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionButtonAction(ActionType.DELETE);
		setDismissDialogOnActionClick(true);
	}
	
	protected boolean hasDataToDelete() { 
		return false;
	}
	
	protected void onDeleteButtonClick() {
	}
	
	@Override
	protected void onActionButtonClick() {
		if(hasDataToDelete())
			onDeleteButtonClick();
	}

	@Override
	protected void onMiddleButtonClick() {
		if(hasDataToDelete() && mDeleteConfirmation) {
			showDeleteConfirmDialog();
		} else {
            super.onMiddleButtonClick();
        }
	}
	
	protected void setDeleteWhatTitle(String deleteWhat) {
		mDeleteWhat = deleteWhat;
		mDeleteConfirmation = true;
	}
	
	protected void setDeleteConfirmation(boolean deleteConfirmation) {
		mDeleteConfirmation = deleteConfirmation;
	}
	
	private void showDeleteConfirmDialog() {
		DialogDeleteConfirmFragment.showDeleteConfirmDialog(mDeleteWhat, DialogCancelDeleteDoneFragment.this, DELETE_CONFIRM, getFragmentManager());
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {	
		switch(requestCode) {
			case DELETE_CONFIRM:
				if(DialogDeleteConfirmFragment.DELETE_CONFIRM.getResultCode().equals(resultCode))
					super.onMiddleButtonClick();
				break;
			default:
				super.onActivityResult(requestCode, resultCode, data);
				break;
		}
	}
}
