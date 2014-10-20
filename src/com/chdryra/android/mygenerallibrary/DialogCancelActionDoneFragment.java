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

/**
 * Standardised 3-button Dialog for "Cancel" (left button), specified action (middle button),
 * "Done" (right button).
 */
@SuppressWarnings("EmptyMethod")
public abstract class DialogCancelActionDoneFragment extends DialogThreeButtonFragment {
    private Button mActionButton;
    private Button mDoneButton;
    private boolean mActionOnDone = false;

    protected abstract View createDialogUI(ViewGroup parent);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeftButtonAction(ActionType.CANCEL);
        setMiddleButtonAction(ActionType.OTHER);
        setRightButtonAction(ActionType.DONE);

        dismissDialogOnLeftClick();
        dismissDialogOnRightClick();
    }

    @Override
    protected void onMiddleButtonClick() {
        onActionButtonClick();
        super.onMiddleButtonClick();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        mActionButton = mMiddleButton;
        mDoneButton = mRightButton;

        return dialog;
    }

    @Override
    protected final void onLeftButtonClick() {
        onCancelButtonClick();
        super.onLeftButtonClick();
    }

    @Override
    protected final void onRightButtonClick() {
        if (mActionOnDone) {
            mActionButton.performClick();
        }
        onDoneButtonClick();
        super.onRightButtonClick();
    }

    protected void onCancelButtonClick() {
    }

    protected void onActionButtonClick() {
    }

    protected void onDoneButtonClick() {
    }

    protected void performActionOnDone() {
        mActionOnDone = true;
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

    public void setKeyboardDoActionOnEditText(EditText editText) {
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

    public void setKeyboardDoDoneOnEditText(EditText editText) {
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
