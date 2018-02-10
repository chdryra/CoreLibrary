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
 * A standard unified look and action for 2 button dialogs. Dialogs often have to do quite similar
 * things that rarely require more than 2 or 3 buttons.
 * <p>
 * Subclasses need to override {@link #createDialogUi()} to return a View
 * (similar to
 * <code>onCreateView(.)</code> in fragments) that defines the look of the Dialog. This method
 * is called by {@link #onCreateDialog(android.os.Bundle)} which combines it with the buttons
 * returned by
 * {@link #getButtons(android.view.ViewGroup)}.
 * </p>
 * <p/>
 * <p>
 * There are a bunch of functions that can be called in the {@link #onCreate(android.os.Bundle)}
 * method when
 * inheriting from this class to tailor the behaviour of the dialog,
 * for example setting dialog title, button labels, button actions,
 * dismiss dialog on button press etc.
 * </p>
 */
public abstract class DialogTwoButtonFragment extends DialogOneButtonFragment {
    public static final ActionType LEFT_BUTTON_DEFAULT_ACTION = ActionType.CANCEL;
    public static final ActionType RIGHT_BUTTON_DEFAULT_ACTION = ActionType.DONE;

    public static final int TWO_BUTTON_LAYOUT = R.layout.dialog_two_button_layout;
    public static final int LEFT_BUTTON = R.id.button_left;
    public static final int RIGHT_BUTTON = R.id.button_right;

    private Button mRightButton;
    private String mRightButtonText;
    private ActivityResultCode mRightButtonResult;

    private boolean mDismissOnRightClick = false;

    public void setRightButtonAction(ActionType action) {
        mRightButtonText = getTitleForAction(action);
        mRightButtonResult = action.getResultCode();
    }

    public void setRightButtonText(String rightButtonText) {
        mRightButtonText = rightButtonText;
    }

    public void clickRightButton() {
        mRightButton.performClick();
    }

    protected void onRightButtonClick() {
        sendResult(mRightButtonResult);
        if (mDismissOnRightClick) dismiss();
    }

    protected void dismissDialogOnRightClick() {
        mDismissOnRightClick = true;
    }

    protected void setRightButton(Button button) {
        mRightButton = button;
        mRightButton.setText(mRightButtonText);
        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightButtonClick();
            }
        });
    }

    @Override
    protected View getButtons(ViewGroup parent) {
        View buttons = getActivity().getLayoutInflater().inflate(TWO_BUTTON_LAYOUT, parent, false);

        setLeftButton((Button) buttons.findViewById(LEFT_BUTTON));
        setRightButton((Button) buttons.findViewById(RIGHT_BUTTON));

        return buttons;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftButtonAction(LEFT_BUTTON_DEFAULT_ACTION);
        setRightButtonAction(RIGHT_BUTTON_DEFAULT_ACTION);
    }
}
