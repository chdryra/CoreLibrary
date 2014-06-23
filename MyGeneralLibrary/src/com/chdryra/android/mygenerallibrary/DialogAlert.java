package com.chdryra.android.mygenerallibrary;

import android.os.Bundle;
import android.view.View;

public abstract class DialogAlert extends DialogTwoButtonFragment {
	public ActionType mActionRight = ActionType.YES;
	public ActionType mActionLeft = ActionType.NO;
	
	protected abstract String getAlertString();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRightButtonAction(mActionRight);
		setLeftButtonAction(mActionLeft);
		setDismissDialogOnRightClick(true);
		setDismissDialogOnLeftClick(true);		
		setDialogTitle(getAlertString());
		showKeyboardOnLaunch(false);
	}
	
	@Override
	protected View createDialogUI() {
		return null;
	}
	
	protected void setRightAction(ActionType action) {
		mActionRight = action;
	}
	
	protected void setLeftAction(ActionType action) {
		mActionLeft = action;
	}
}
