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
    private boolean mActionOnDone = false;

    protected abstract View createDialogUI(ViewGroup parent);

    @Override
    protected void onMiddleButtonClick() {
        onActionButtonClick();
        super.onMiddleButtonClick();
    }

    protected void onActionButtonClick() {
    }

    protected void performActionOnDone() {
        mActionOnDone = true;
    }

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
    protected void onLeftButtonClick() {
        onCancelButtonClick();
        super.onLeftButtonClick();
    }

    protected void onCancelButtonClick() {
    }

    @Override
    protected void onRightButtonClick() {
        if (mActionOnDone) {
            mActionButton.performClick();
        }
        onDoneButtonClick();
        super.onRightButtonClick();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        mActionButton = mMiddleButton;
        mDoneButton = mRightButton;

        return dialog;
    }

    protected void onDoneButtonClick() {
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
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    mActionButton.performClick();
                }
                return false;
            }
        });
    }

    protected void setKeyboardIMEDoDone(EditText editText) {
        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mDoneButton.performClick();
                }
                return false;
            }
        });
    }
}
