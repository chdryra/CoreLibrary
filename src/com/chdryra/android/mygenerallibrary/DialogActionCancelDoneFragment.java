/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

@SuppressWarnings("EmptyMethod")
public abstract class DialogActionCancelDoneFragment extends DialogThreeButtonFragment {
	
	private boolean mActionOnDone = false;
	protected abstract View createDialogUI(); 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setMiddleButtonAction(ActionType.CANCEL);
		setRightButtonAction(ActionType.DONE);
		setDismissDialogOnMiddleClick(true);
		setDismissDialogOnRightClick(true);
		
		setLeftButtonAction(ActionType.OTHER);
		setDismissDialogOnLeftClick(false);
	}
	
	@Override
	protected void onLeftButtonClick() {
		onActionButtonClick();
		super.onLeftButtonClick();
	}
	
	@Override
	protected void onMiddleButtonClick() {
		onCancelButtonClick();
		super.onMiddleButtonClick();
	}

	@Override
	protected void onRightButtonClick() {
		if(mActionOnDone)
			mLeftButton.performClick();
		onDoneButtonClick();
		super.onRightButtonClick();
	}
	
	protected void onActionButtonClick() {
	}
	
	protected void onCancelButtonClick() {
	}

    protected void onDoneButtonClick() {
	}

    protected void setActionOnDone(boolean actionOnDone) {
		mActionOnDone = actionOnDone;
	}
	
	public void setActionButtonText(String actionButtonText) {
		setLeftButtonText(actionButtonText);
	}
	
	public void setActionButtonAction(ActionType action) {
		setLeftButtonAction(action);
	}
	
	protected void setDismissDialogOnActionClick(boolean dismiss) {
		setDismissDialogOnLeftClick(dismiss);
	}
	
	protected void setKeyboardIMEDoAction(EditText editText) {
		editText.setImeOptions(EditorInfo.IME_ACTION_GO);
		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
	        @Override
	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
	        {
	            if(actionId == EditorInfo.IME_ACTION_GO)
	            	mLeftButton.performClick();
	            return false;
	        }
	    });
	}
	
	protected void setKeyboardIMEDoDone(EditText editText) {
		editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
	        @Override
	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
	        {
	            if(actionId == EditorInfo.IME_ACTION_DONE)
	            	mRightButton.performClick();
	            return false;
	        }
	    });
	}	
}
