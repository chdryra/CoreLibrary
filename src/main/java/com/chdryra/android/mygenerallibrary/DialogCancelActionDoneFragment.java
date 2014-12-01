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

/**
 * Standard 3-button Dialog for "Cancel" (left button), specified action (middle button),
 * "Done" (right button).
 */
@SuppressWarnings("EmptyMethod")
public abstract class DialogCancelActionDoneFragment extends DialogThreeButtonFragment {
    public static final ActionType CANCEL_ACTION      = ActionType.CANCEL;
    public static final ActionType ACTION_ACTION      = ActionType.OTHER;
    public static final ActionType DONE_ACTION        = ActionType.DONE;
    public static final int        KEYBOARD_DO_ACTION = EditorInfo.IME_ACTION_GO;
    public static final int        KEYBOARD_DO_DONE   = EditorInfo.IME_ACTION_DONE;

    private boolean mActionOnDone = false;

    @Override
    protected abstract View createDialogUI();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeftButtonAction(CANCEL_ACTION);
        setMiddleButtonAction(ACTION_ACTION);
        setRightButtonAction(DONE_ACTION);

        dismissDialogOnLeftClick();
        dismissDialogOnRightClick();
    }

    @Override
    protected void onMiddleButtonClick() {
        onActionButtonClick();
        super.onMiddleButtonClick();
    }

    public void setKeyboardDoActionOnEditText(EditText editText) {
        editText.setImeOptions(KEYBOARD_DO_ACTION);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KEYBOARD_DO_ACTION) {
                    clickActionButton();
                }
                return false;
            }
        });
    }

    public void setKeyboardDoDoneOnEditText(EditText editText) {
        editText.setImeOptions(KEYBOARD_DO_DONE);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KEYBOARD_DO_DONE) {
                    clickDoneButton();
                }
                return false;
            }
        });
    }

    public void clickCancelButton() {
        clickLeftButton();
    }

    public void clickActionButton() {
        clickMiddleButton();
    }

    public void clickDoneButton() {
        clickRightButton();
    }

    public String getActionButtonText() {
        return getMiddleButtonText();
    }

    protected void setActionButtonText(String actionButtonText) {
        setMiddleButtonText(actionButtonText);
    }

    @Override
    protected final void onLeftButtonClick() {
        onCancelButtonClick();
        super.onLeftButtonClick();
    }

    @Override
    protected final void onRightButtonClick() {
        if (mActionOnDone) {
            clickActionButton();
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

    protected void setActionButtonAction(ActionType action) {
        setMiddleButtonAction(action);
    }

    protected void dismissDialogOnActionClick() {
        dismissDialogOnMiddleClick();
    }
}
