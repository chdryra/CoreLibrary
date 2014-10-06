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
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A standard unified look and action for 3 button dialogs. Dialogs often have to do quite similar
 * things that rarely require more than 2 or 3 buttons.
 * <p>
 * Subclasses need to override <code>createDialogUI()</code> to return a View (similar to
 * <code>onCreateView(.)</code> in fragments) that defines the look of the Dialog. This method
 * is called by <code>onCreateDialog(.)</code>.
 * </p>
 * <p/>
 * <p>o
 * There are a bunch of functions that can be called in the <code>onCreate(.)</code> method when
 * inheriting from this class to tailor the behaviour of the dialog for example dialog title,
 * button labels, button actions, dismiss dialog on button press etc.
 * </p>
 *
 * @see #createDialogUI(android.view.ViewGroup)
 * @see #onCreateDialog(android.os.Bundle)
 */
public abstract class DialogThreeButtonFragment extends DialogTwoButtonFragment {
    protected Button             mMiddleButton;
    private   String             mMiddleButtonText;
    private   ActivityResultCode mMiddleButtonResult;
    private boolean mDismissOnMiddleClick = false;

    @Override
    protected abstract View createDialogUI(ViewGroup parent);

    /**
     * By default sets up left button as "Cancel", middle as "Other" and right button as "Done". Can
     * override this to set up other button actions and button/dialog behaviour to tailor the
     * Dialog. See the other methods for parameters that can be set.
     * @param savedInstanceState
     *
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMiddleButtonAction(ActionType.OTHER);
    }

    @Override
    protected void sendResult(ActivityResultCode resultCode) {
        super.sendResult(resultCode);
        if (resultCode.equals(mMiddleButtonResult) && mDismissOnMiddleClick) {
            dismiss();
        }
    }

    @Override
    protected View getButtons(ViewGroup parent) {
        View buttons = getActivity().getLayoutInflater().inflate(
                R.layout.dialog_three_button_layout, parent, false);

        mLeftButton = (Button) buttons.findViewById(R.id.button_left);
        mMiddleButton = (Button) buttons.findViewById(R.id.button_middle);
        mRightButton = (Button) buttons.findViewById(R.id.button_right);

        mLeftButton.setText(mLeftButtonText);
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftButtonClick();
            }
        });

        mMiddleButton.setText(mMiddleButtonText);
        mMiddleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMiddleButtonClick();
            }
        });

        mRightButton.setText(mRightButtonText);
        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightButtonClick();
            }
        });

        return buttons;
    }

    /**
     * By default sends the ActivityResultCode bound to the middle button.
     *
     * @see #setMiddleButtonAction(DialogTwoButtonFragment.ActionType)
     * @see #setMiddleButtonResultCode(ActivityResultCode)
     */
    protected void onMiddleButtonClick() {
        sendResult(mMiddleButtonResult);
    }

    /**
     * The result code to bind to the middle button. By default, <code>onMiddleButtonClick()</code>
     * sends this result code to the commissioning activity.
     * @param resultCode: the ActivityResultCode to bind to the button.
     *
     * @see #onMiddleButtonClick()
     * @see ActivityResultCode
     */
    protected void setMiddleButtonResultCode(ActivityResultCode resultCode) {
        mMiddleButtonResult = resultCode;
    }

    /**
     * The text label to show on the middle button.
     *
     * @param middleButtonText: the button label.
     */
    protected void setMiddleButtonText(String middleButtonText) {
        mMiddleButtonText = middleButtonText;
    }

    /**
     * Can bind predefined ActivityResultCodes and labels using the ActionType enum.
     * @param action: the action type to bind to the button.
     *
     * @see DialogTwoButtonFragment.ActionType
     */
    protected void setMiddleButtonAction(ActionType action) {
        mMiddleButtonText = getTitleForAction(action);
        mMiddleButtonResult = action.getResultCode();
    }

    protected void dismissDialogOnMiddleClick() {
        mDismissOnMiddleClick = true;
    }
}
