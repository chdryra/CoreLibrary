/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Dialogs;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chdryra.android.corelibrary.OtherUtils.ActivityResultCode;
import com.chdryra.android.corelibrary.R;

/**
 * 3 button extension of {@link DialogTwoButtonFragment}
 */
public abstract class DialogThreeButtonFragment extends DialogTwoButtonFragment {
    public static final ActionType MIDDLE_BUTTON_DEFAULT_ACTION = ActionType.OTHER;

    public static final int THREE_BUTTON_LAYOUT = R.layout.dialog_three_button_layout;
    public static final int LEFT_BUTTON = R.id.button_left;
    public static final int MIDDLE_BUTTON = R.id.button_middle;
    public static final int RIGHT_BUTTON = R.id.button_right;

    private Button mMiddleButton;
    private String mMiddleButtonText;
    private ActivityResultCode mMiddleButtonResult;
    private boolean mDismissOnMiddleClick = false;
    private boolean mHideMiddleButton = false;

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

    @Override
    protected abstract View createDialogUi();

    @Override
    protected View getButtons(ViewGroup parent) {
        View buttons = getActivity().getLayoutInflater().inflate(THREE_BUTTON_LAYOUT, parent, false);

        setLeftButton((Button) buttons.findViewById(LEFT_BUTTON));
        setMiddleButton((Button) buttons.findViewById(MIDDLE_BUTTON));
        setRightButton((Button) buttons.findViewById(RIGHT_BUTTON));

        return buttons;
    }

    private void setMiddleButton(Button button) {
        mMiddleButton = button;
        mMiddleButton.setText(mMiddleButtonText);
        mMiddleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMiddleButtonClick();
            }
        });

        if(mHideMiddleButton) hideMiddleButton();
    }

    private void hideMiddleButton() {
        mMiddleButton.setVisibility(View.GONE);
    }

    protected void setHideMiddleButton() {
        if(mMiddleButton == null) {
            mHideMiddleButton = true;
        } else {
            hideMiddleButton();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMiddleButtonAction(MIDDLE_BUTTON_DEFAULT_ACTION);
    }
}
