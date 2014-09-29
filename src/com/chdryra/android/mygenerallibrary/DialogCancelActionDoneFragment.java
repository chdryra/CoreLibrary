/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;


import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressWarnings("EmptyMethod")
public abstract class DialogCancelActionDoneFragment extends DialogThreeButtonFragment {
    private Button mActionButton;
    private Button mDoneButton;

    protected abstract View createDialogUI(ViewGroup parent);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setLeftButtonAction(ActionType.CANCEL);
        dismissDialogOnLeftClick();
        setMiddleButtonAction(ActionType.OTHER);
        setRightButtonAction(ActionType.DONE);
		dismissDialogOnRightClick();
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        mActionButton = mMiddleButton;
        mDoneButton = mRightButton;

        return dialog;
    }

    @SuppressWarnings("WeakerAccess")
    protected void onCancelButtonClick() {
    }

    protected void onActionButtonClick() {
    }

    protected void onDoneButtonClick() {
    }

    @Override
	protected void onLeftButtonClick() {
		onCancelButtonClick();
		super.onLeftButtonClick();
	}
	
	@Override
	protected void onMiddleButtonClick() {
		onActionButtonClick();
		super.onMiddleButtonClick();
	}

	@Override
	protected void onRightButtonClick() {
        mActionButton.performClick();
		onDoneButtonClick();
		super.onRightButtonClick();
	}

	protected void setActionButtonText(String actionButtonText) {
		setMiddleButtonText(actionButtonText);
	}
	
	protected void setActionButtonAction(ActionType action) {
		setMiddleButtonAction(action);
	}
	
	protected void dismissDialogOnActionClick() {
		dismissDialogOnMiddleClick();
	}
	
	protected void setKeyboardIMEDoAction(EditText editText) {
		editText.setImeOptions(EditorInfo.IME_ACTION_GO);
		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
	        @Override
	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
	        {
	            if(actionId == EditorInfo.IME_ACTION_GO)
                    mActionButton.performClick();
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
                    mDoneButton.performClick();
	            return false;
	        }
	    });
	}
}
