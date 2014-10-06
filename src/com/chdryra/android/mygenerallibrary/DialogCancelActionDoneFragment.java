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

    /**
     * By default calls <code>onActionButtonClick()</code> and sends appropriate ActivityResultCode.
     */
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

    /**
     * By default calls <code>onCancelButtonClick()</code> and sends appropriate ActivityResultCode.
     */
    @Override
    protected void onLeftButtonClick() {
        onCancelButtonClick();
        super.onLeftButtonClick();
    }

    /**
     * By default calls <code>onDoneButtonClick()</code> and sends appropriate ActivityResultCode.
     */
    @Override
    protected void onRightButtonClick() {
        if (mActionOnDone) {
            mActionButton.performClick();
        }
        onDoneButtonClick();
        super.onRightButtonClick();
    }

    /**
     * Action to perform when Cancel is pressed, in addition to sending the "Cancel"
     * ActivityResultCode to the commissioning activity. By default does nothing.
     */
    protected void onCancelButtonClick() {
    }

    /**
     * Called when middle button is clicked, in addition to sending "Other" ActivityResultCode to
     * the commissioning activity. By default does nothing.
     */
    protected void onActionButtonClick() {
    }

    /**
     * Action to perform when Done is pressed, in addition to sending the "Done"
     * ActivityResultCode to the commissioning activity. By default does nothing.
     */
    protected void onDoneButtonClick() {
    }

    /**
     * If called in <code>onCreate(.)</code> will ensure middle button is also clicked when Done
     * button is pressed, for example completing an "Add" action as part of the "Done" click.
     */
    protected void performActionOnDone() {
        mActionOnDone = true;
    }

    /**
     * Label for action button.
     *
     * @param actionButtonText
     */
    protected void setActionButtonText(String actionButtonText) {
        setMiddleButtonText(actionButtonText);
    }

    /**
     * Can bind predefined ActivityResultCodes and labels using the ActionType enum.
     * @param action: the action type to bind to the button.
     *
     * @see DialogTwoButtonFragment.ActionType
     */
    protected void setActionButtonAction(ActionType action) {
        setMiddleButtonAction(action);
    }

    protected void dismissDialogOnActionClick() {
        dismissDialogOnMiddleClick();
    }

    /**
     * Set "Go" on the keyboard return key. Pressing "Go" will click the action button.
     * @param editText: the EditText on which to show the "Go" button.
     */
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

    /**
     * Set "Done" on the keyboard return key. Pressing "Done" will click the done button.
     * @param editText: the EditText on which to show the "Done" button.
     */
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
