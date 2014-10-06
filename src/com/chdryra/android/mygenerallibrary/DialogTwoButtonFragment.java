/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * A standard unified look and action for 2 button dialogs. Dialogs often have to do quite similar
 * things that rarely require more than 2 or 3 buttons.
 * <p>
 * Subclasses need to override <code>createDialogUI()</code> to return a View (similar to
 * <code>onCreateView(.)</code> in fragments) that defines the look of the Dialog. This method
 * is called by <code>onCreateDialog(.)</code>.
 * </p>
 * <p/>
 * <p>
 * There are a bunch of functions that can be called in the <code>onCreate(.)</code> method when
 * inheriting from this class to tailor the behaviour of the dialog for example dialog title,
 * button labels, button actions, dismiss dialog on button press etc.
 * </p>
 *
 * @see #createDialogUI(android.view.ViewGroup)
 * @see #onCreateDialog(android.os.Bundle)
 * @see DialogThreeButtonFragment
 * @see DialogAlert
 */
public abstract class DialogTwoButtonFragment extends DialogFragment {
    protected Button mLeftButton;
    protected Button mRightButton;

    protected String mLeftButtonText;
    protected String mRightButtonText;

    private ActivityResultCode mLeftButtonResult;
    private ActivityResultCode mRightButtonResult;

    private boolean mDismissOnLeftClick  = false;
    private boolean mDismissOnRightClick = false;

    private String mDialogTitle;
    private Intent mReturnData;

    private boolean mNoTitle              = true;
    private boolean mShowKeyboardOnLaunch = true;

    /**
     * Subclasses need to override this to return the dialog UI to show above the Dialog buttons.
     * @param parent: by default a LinearLayout that will hold the return View followed by the
     *              buttons. If the View is inflated from an XML file using a layout inflater,
     *              "parent" should be passed as the parent ViewGroup parameter to the inflater.
     * @return View: the view to show above the buttons in the dialog.
     *
     * @see #onCreateDialog(android.os.Bundle)
     */
    protected abstract View createDialogUI(ViewGroup parent);

    /**
     * By default sets up left button as "Cancel" and right button as "DONE". Can override this to
     * set up other button actions and button/dialog behaviour to tailor the Dialog. See the other
     * methods for parameters that can be set.
     * @param savedInstanceState
     *
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftButtonAction(ActionType.CANCEL);
        setRightButtonAction(ActionType.DONE);
    }

    /**
     * Takes output of <code>createDialogUI(.)</code>, adds buttons from <code>getButtons(.)
     * </code> and builds and returns a Dialog. Can override the method <code>onCreate(.)</code>
     * to set other parameters using the methods available in this class. This method will be
     * called afterwards.
     * @param savedInstanceState
     * @return Dialog: Dialog built using <code>createDialogUI(.)</code> and
     * <code>getButtons(.)</code>.
     *
     * @see #createDialogUI(android.view.ViewGroup)
     * @see #getButtons(android.view.ViewGroup)
     * @see #onCreate(android.os.Bundle)
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return buildDialog();
    }

    /**
     * Overrides super method: hides keyboard on dialog dismissal.
     */
    @Override
    public void onStop() {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onStop();
    }

    /**
     * By default sends the ActivityResultCode bound to the left button.
     *
     * @see #setLeftButtonAction(ActionType)
     * @see #setLeftButtonResultCode(ActivityResultCode)
     */
    protected void onLeftButtonClick() {
        sendResult(mLeftButtonResult);
    }

    /**
     * By default sends the ActivityResultCode bound to the right button.
     *
     * @see #setRightButtonAction(DialogTwoButtonFragment.ActionType)
     * @see #setRightButtonResultCode(ActivityResultCode)
     */
    protected void onRightButtonClick() {
        sendResult(mRightButtonResult);
    }

    /**
     * The result code to bind to the left button. By default, <code>onLeftButtonClick()</code>
     * sends this result code to the commissioning activity.
     * @param resultCode: the ActivityResultCode to bind to the button.
     *
     * @see #onLeftButtonClick()
     * @see ActivityResultCode
     */
    protected void setLeftButtonResultCode(ActivityResultCode resultCode) {
        mLeftButtonResult = resultCode;
    }

    /**
     * The result code to bind to the right button. By default, <code>onRightButtonClick()</code>
     * sends this result code to the commissioning activity.
     *
     * @param resultCode: the ActivityResultCode to bind to the button.
     * @see #onRightButtonClick()
     * @see ActivityResultCode
     */
    protected void setRightButtonResultCode(ActivityResultCode resultCode) {
        mRightButtonResult = resultCode;
    }

    /**
     * The text label to show on the left button.
     *
     * @param leftButtonText: the button label.
     */
    public void setLeftButtonText(String leftButtonText) {
        mLeftButtonText = leftButtonText;
    }

    /**
     * The text label to show on the right button.
     * @param rightButtonText: the button label.
     */
    public void setRightButtonText(String rightButtonText) {
        mRightButtonText = rightButtonText;
    }

    /**
     * Can bind predefined ActivityResultCodes and labels using the ActionType enum.
     * @param action: the action type to bind to the button.
     *
     * @see DialogTwoButtonFragment.ActionType
     */
    public void setLeftButtonAction(ActionType action) {
        mLeftButtonText = getTitleForAction(action);
        mLeftButtonResult = action.getResultCode();
    }

    /**
     * Can bind predefined ActivityResultCodes and labels using the ActionType enum.
     * @param action: the action type to bind to the button.
     *
     * @see DialogTwoButtonFragment.ActionType
     */
    public void setRightButtonAction(ActionType action) {
        mRightButtonText = getTitleForAction(action);
        mRightButtonResult = action.getResultCode();
    }

    /**
     * Looks up titles for the given ActionType. By default uses the labels bound to that
     * ActionType.
     * @param type: ActionType to find the label for.
     * @return String: the label.
     */
    protected String getTitleForAction(ActionType type) {
        return type.getLabel(getActivity());
    }

    protected void setDialogTitle(String dialogTitle) {
        if (dialogTitle != null) {
            mDialogTitle = dialogTitle;
            mNoTitle = false;
        } else {
            mNoTitle = true;
        }
    }

    protected void hideKeyboardOnLaunch() {
        mShowKeyboardOnLaunch = false;
    }

    protected void dismissDialogOnLeftClick() {
        mDismissOnLeftClick = true;
    }

    protected void dismissDialogOnRightClick() {
        mDismissOnRightClick = true;
    }

    /**
     * Initialises and returns new Intent data that will be passed to the commissioning activity
     * if a button is pressed.
     * @return Intent: holds the return data.
     */
    protected Intent getNewReturnData() {
        mReturnData = new Intent();
        return mReturnData;
    }

    /**
     * Gets the currently held Intent data that will be returned to the commissioning activity if
     * a button is pressed.
     * @return Intent: holds the return data.
     */
    protected Intent getReturnData() {
        if (mReturnData == null) {
            return getNewReturnData();
        } else {
            return mReturnData;
        }
    }

    /**
     * Sends the ActivityResultCode passed to the commissioning activity and dismisses Dialog if
     * necessary.
     * @param resultCode: the result code to send to the commissioning activity.
     */
    protected void sendResult(ActivityResultCode resultCode) {
        if (getTargetFragment() == null) {
            return;
        }

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode.get(),
                getReturnData());
        mReturnData = null;

        if (resultCode.equals(mLeftButtonResult) && mDismissOnLeftClick) {
            dismiss();
        }

        if (resultCode.equals(mRightButtonResult) && mDismissOnRightClick) {
            dismiss();
        }
    }

    /**
     * Returns a button layout with appropriate actions and labels. Called by
     * <code>onCreateDialog(.)</code>.
     * @param parent: by default a LinearLayout that will hold the return View followed by the
     *              buttons.
     *              If the View is inflated from an XML file using a layout inflater, "parent"
     *              should be passed as the parent ViewGroup parameter to the inflater.
     * @return View: the main UI for the Dialog.
     *
     * @see #onCreateDialog(android.os.Bundle)
     */
    protected View getButtons(ViewGroup parent) {
        View buttons = getActivity().getLayoutInflater().inflate(
                R.layout.dialog_two_button_layout, parent, false);

        mLeftButton = (Button) buttons.findViewById(R.id.button_left);
        mRightButton = (Button) buttons.findViewById(R.id.button_right);

        mLeftButton.setText(mLeftButtonText);
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftButtonClick();
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

    private Dialog buildDialog() {
        Dialog dialog = new Dialog(getActivity());

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
                1.0f);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(lp);

        //Hacky layout params to get listview dialogUIs to render properly.
        //Need to set layout weight of 1 on it...
        View dialogUi = createDialogUI(layout);
        if (dialogUi != null) {
            layout.addView(dialogUi, lp1);
        }
        layout.addView(getButtons(layout), lp);

        if (mNoTitle) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        dialog.setContentView(layout);
        dialog.setTitle(mDialogTitle);

        if (mShowKeyboardOnLaunch) {
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams
                    .SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        return dialog;
    }

    /**
     * Enum that provides ActionResultCodes and associated labels for some common actions.
     *
     * @see ActivityResultCode
     */
    public static enum ActionType {
        CANCEL(ActivityResultCode.CANCEL, R.string.gl_action_cancel_text),
        DONE(ActivityResultCode.DONE, R.string.gl_action_done_text),
        OTHER(ActivityResultCode.OTHER, R.string.gl_action_other_text),
        EDIT(ActivityResultCode.EDIT, R.string.gl_action_edit_text),
        ADD(ActivityResultCode.ADD, R.string.gl_action_add_text),
        DELETE(ActivityResultCode.DELETE, R.string.gl_action_delete_text),
        CLEAR(ActivityResultCode.CLEAR, R.string.gl_action_clear_text),
        OK(ActivityResultCode.OK, R.string.gl_action_ok_text),
        YES(ActivityResultCode.YES, R.string.gl_action_yes_text),
        NO(ActivityResultCode.NO, R.string.gl_action_no_text);

        private final ActivityResultCode mResultCode;
        private final int                mLabelId;

        private ActionType(ActivityResultCode resultCode, int labelId) {
            mResultCode = resultCode;
            mLabelId = labelId;
        }

        /**
         * Returns the ActivityResultCode associated with the ActionType.
         * @return ActivityResultCode
         */
        public ActivityResultCode getResultCode() {
            return mResultCode;
        }

        /**
         * Returns the text label associates with the ActionType.
         * @param context: needed to access the string resources XML file.
         * @return String: the label.
         */
        public String getLabel(Context context) {
            return context.getResources().getString(mLabelId);
        }
    }
}
