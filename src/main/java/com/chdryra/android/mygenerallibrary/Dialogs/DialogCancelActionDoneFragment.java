/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Dialogs;


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
    public static final ActionType CANCEL_ACTION = ActionType.CANCEL;
    public static final ActionType ACTION_ACTION = ActionType.OTHER;
    public static final ActionType DONE_ACTION = ActionType.DONE;
    public static final int KEYBOARD_DO_ACTION = EditorInfo.IME_ACTION_GO;
    public static final int KEYBOARD_DO_DONE = EditorInfo.IME_ACTION_DONE;

    private boolean mActionOnDone = false;

//public methods
    public String getActionButtonText() {
        return getMiddleButtonText();
    }

    protected void setActionButtonText(String actionButtonText) {
        setMiddleButtonText(actionButtonText);
    }

    public int setKeyboardDoActionOnEditText(EditText editText) {
        editText.setImeOptions(KEYBOARD_DO_ACTION);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            //Overridden
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KEYBOARD_DO_ACTION) clickActionButton();
                return false;
            }
        });

        return KEYBOARD_DO_ACTION;
    }

    public void setKeyboardDoDoneOnEditText(EditText editText) {
        editText.setImeOptions(KEYBOARD_DO_DONE);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KEYBOARD_DO_DONE) clickDoneButton();
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

//Overridden
    @Override
    protected void onMiddleButtonClick() {
        onActionButtonClick();
        super.onMiddleButtonClick();
    }

    @Override
    protected abstract View createDialogUi();

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
}
