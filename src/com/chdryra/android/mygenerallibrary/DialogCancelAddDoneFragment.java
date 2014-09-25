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

public abstract class DialogCancelAddDoneFragment extends DialogCancelActionDoneFragment {

	protected abstract View createDialogUI();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionButtonAction(ActionType.ADD);
		setDismissDialogOnActionClick(false);
	}
	
	@Override
	protected void onActionButtonClick() {
		OnAddButtonClick();
	}
	
	protected void setAddOnDone(boolean addOnDone) {
		setActionOnDone(addOnDone);
	}
	
	protected void OnAddButtonClick() {
	}
}
