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
 * Subclasses need to override {@link #createDialogUI()} to return a View
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
     * Enum that provides ActionResultCodes and associated text labels for some common actions.
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

        public ActivityResultCode getResultCode() {
            return mResultCode;
        }

        public String getLabel(Context context) {
            return context.getResources().getString(mLabelId);
        }
    }

    /**
     * Subclasses need to override this to return the dialog UI to show above the Dialog buttons.
     *
     * @return View: the main UI to show above the buttons in the dialog.
     * @see #onCreateDialog(android.os.Bundle)
     * @see #getButtons(android.view.ViewGroup)
     */
    protected abstract View createDialogUI();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftButtonAction(ActionType.CANCEL);
        setRightButtonAction(ActionType.DONE);
    }

    /**
     * Calls {@link #buildDialog()}
     *
     * @param savedInstanceState: instance state from rotations etc.
     * @return Dialog: built dialog object
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return buildDialog();
    }

    @Override
    public void onStop() {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onStop();
    }

    protected void onLeftButtonClick() {
        sendResult(mLeftButtonResult);
    }

    protected void onRightButtonClick() {
        sendResult(mRightButtonResult);
    }

    public void setLeftButtonAction(ActionType action) {
        mLeftButtonText = getTitleForAction(action);
        mLeftButtonResult = action.getResultCode();
    }

    public void setRightButtonAction(ActionType action) {
        mRightButtonText = getTitleForAction(action);
        mRightButtonResult = action.getResultCode();
    }

    protected String getTitleForAction(ActionType type) {
        return type.getLabel(getActivity());
    }

    public void setDialogTitle(String dialogTitle) {
        if (dialogTitle != null) {
            mDialogTitle = dialogTitle;
            mNoTitle = false;
        } else {
            mNoTitle = true;
        }
    }

    public void hideKeyboardOnLaunch() {
        mShowKeyboardOnLaunch = false;
    }

    protected void dismissDialogOnLeftClick() {
        mDismissOnLeftClick = true;
    }

    protected void dismissDialogOnRightClick() {
        mDismissOnRightClick = true;
    }

    protected Intent createNewReturnData() {
        mReturnData = new Intent();
        return mReturnData;
    }

    protected Intent getReturnData() {
        if (mReturnData == null) {
            return createNewReturnData();
        } else {
            return mReturnData;
        }
    }

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
     * Returns a button layout with appropriate actions and labels.
     *
     * @param parent: by default a LinearLayout that will hold the return View followed by the
     *                buttons. If the View is inflated from an XML file using a layout inflater,
     *                "parent" should be passed as the parent ViewGroup parameter to the inflater.
     * @return View: the button UI for the Dialog.
     * @see #onCreateDialog(android.os.Bundle)
     * @see #createDialogUI()
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
        View dialogUi = createDialogUI();
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

    void clickLeftButton() {
        mLeftButton.performClick();
    }

    void clickRightButton() {
        mRightButton.performClick();
    }
}
