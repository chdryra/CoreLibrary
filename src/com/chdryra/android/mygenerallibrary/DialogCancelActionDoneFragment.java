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

    private Button mCancelButton;
	private Button mActionButton;
    private Button mDoneButton;
    private boolean mActionOnDone = false;

    protected abstract View createDialogUI(ViewGroup parent);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setLeftButtonAction(ActionType.CANCEL);
        setDismissDialogOnLeftClick(true);
        setMiddleButtonAction(ActionType.OTHER);
        setDismissDialogOnMiddleClick(false);
        setRightButtonAction(ActionType.DONE);
		setDismissDialogOnRightClick(true);
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        mCancelButton = mLeftButton;
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
		if(mActionOnDone)
			clickActionButton();
		onDoneButtonClick();
		super.onRightButtonClick();
	}

    @SuppressWarnings("WeakerAccess")
    protected void setActionOnDone(boolean actionOnDone) {
		mActionOnDone = actionOnDone;
	}
	
	protected void setActionButtonText(String actionButtonText) {
		setMiddleButtonText(actionButtonText);
	}
	
	protected void setActionButtonAction(ActionType action) {
		setMiddleButtonAction(action);
	}
	
	protected void setDismissDialogOnActionClick(boolean dismiss) {
		setDismissDialogOnMiddleClick(dismiss);
	}
	
	protected void setKeyboardIMEDoAction(EditText editText) {
		editText.setImeOptions(EditorInfo.IME_ACTION_GO);
		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
	        @Override
	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
	        {
	            if(actionId == EditorInfo.IME_ACTION_GO)
	            	clickActionButton();
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
	            	clickDoneButton();
	            return false;
	        }
	    });
	}

    public void clickCancelButton() {
        mCancelButton.performClick();
    }

    @SuppressWarnings("WeakerAccess")
    public void clickActionButton() {
        mActionButton.performClick();
    }

    @SuppressWarnings("WeakerAccess")
    public void clickDoneButton() {
        mDoneButton.performClick();
    }
}
