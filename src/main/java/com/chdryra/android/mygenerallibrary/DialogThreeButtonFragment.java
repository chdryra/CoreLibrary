/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 3 button extension of {@link com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment}
 */
public abstract class DialogThreeButtonFragment extends DialogTwoButtonFragment {
    public static final ActionType MIDDLE_BUTTON_DEFAULT_ACTION = ActionType.OTHER;

    private Button mMiddleButton;
    private String mMiddleButtonText;
    private ActivityResultCode mMiddleButtonResult;
    private boolean mDismissOnMiddleClick = false;

//public methods
    public String getMiddleButtonText() {
        return (String) mMiddleButton.getText();
    }

    protected void setMiddleButtonText(String middleButtonText) {
        mMiddleButtonText = middleButtonText;
    }

    public void clickMiddleButton() {
        mMiddleButton.performClick();
    }

    protected void onMiddleButtonClick() {
        sendResult(mMiddleButtonResult);
        if (mDismissOnMiddleClick) dismiss();
    }

    protected void setMiddleButtonAction(ActionType action) {
        mMiddleButtonText = getTitleForAction(action);
        mMiddleButtonResult = action.getResultCode();
    }

    protected void dismissDialogOnMiddleClick() {
        mDismissOnMiddleClick = true;
    }

//Overridden
    @Override
    protected abstract View createDialogUi();

    @Override
    protected void sendResult(ActivityResultCode resultCode) {
        super.sendResult(resultCode);

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
//Overridden
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMiddleButtonAction(MIDDLE_BUTTON_DEFAULT_ACTION);
    }
}
