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
 * is called by <code>onCreateDialog(.)</code> which combines it with the buttons.
 * </p>
 * <p/>
 * <p>
 * There are a bunch of functions that can be called in the <code>onCreate(.)</code> method when
 * inheriting from this class to tailor the behaviour of the dialog, for example dialog title,
 * button labels, button actions, dismiss dialog on button press etc.
 * </p>
 */
public abstract class DialogThreeButtonFragment extends DialogTwoButtonFragment {
    protected Button             mMiddleButton;
    private   String             mMiddleButtonText;
    private   ActivityResultCode mMiddleButtonResult;
    private boolean mDismissOnMiddleClick = false;

    @Override
    protected abstract View createDialogUI(ViewGroup parent);

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

    protected void onMiddleButtonClick() {
        sendResult(mMiddleButtonResult);
    }

    protected void setMiddleButtonResultCode(ActivityResultCode resultCode) {
        mMiddleButtonResult = resultCode;
    }

    protected void setMiddleButtonText(String middleButtonText) {
        mMiddleButtonText = middleButtonText;
    }

    protected void setMiddleButtonAction(ActionType action) {
        mMiddleButtonText = getTitleForAction(action);
        mMiddleButtonResult = action.getResultCode();
    }

    protected void dismissDialogOnMiddleClick() {
        mDismissOnMiddleClick = true;
    }
}
