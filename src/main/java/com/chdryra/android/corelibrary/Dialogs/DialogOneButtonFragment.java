/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.chdryra.android.corelibrary.OtherUtils.ActivityResultCode;
import com.chdryra.android.corelibrary.R;

/**
 * A standard unified look and action for 1 button dialogs. Dialogs often have to do quite similar
 * things that rarely require more than a few buttons.
 * <p>
 * Subclasses need to override {@link #createDialogUi()} to return a View
 * (similar to
 * <code>onCreateView(.)</code> in fragments) that defines the look of the Dialog. This method
 * is called by {@link #onCreateDialog(Bundle)} which combines it with the buttons
 * returned by
 * {@link #getButtons(ViewGroup)}.
 * </p>
 * <p/>
 * <p>
 * There are a bunch of functions that can be called in the {@link #onCreate(Bundle)}
 * method when
 * inheriting from this class to tailor the behaviour of the dialog,
 * for example setting dialog title, button labels, button actions,
 * dismiss dialog on button press etc.
 * </p>
 */
public abstract class DialogOneButtonFragment extends DialogFragment {
    public static final ActionType LEFT_BUTTON_DEFAULT_ACTION = ActionType.DONE;

    public static final int ONE_BUTTON_LAYOUT = R.layout.dialog_one_button_layout;
    public static final int BUTTON = R.id.button_left;

    private Button mLeftButton;
    private String mLeftButtonText;
    private ActivityResultCode mLeftButtonResult;

    private boolean mDismissOnLeftClick = false;

    private String mDialogTitle;
    private Intent mReturnData;

    private boolean mShowKeyboardOnLaunch = true;

    /**
     * Enum that provides ActionResultCodes and associated text labels for some common actions.
     *
     * @see ActivityResultCode
     */
    public enum ActionType {
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
        private final int mLabelId;

        ActionType(ActivityResultCode resultCode, int labelId) {
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
     * @see #onCreateDialog(Bundle)
     * @see #getButtons(ViewGroup)
     */
    @Nullable
    protected abstract View createDialogUi();

    public boolean isShowing() {
        return getDialog() != null && getDialog().isShowing();
    }

    public void setLeftButtonAction(ActionType action) {
        mLeftButtonText = getTitleForAction(action);
        mLeftButtonResult = action.getResultCode();
    }

    public void setDialogTitle(@Nullable String dialogTitle) {
        mDialogTitle = dialogTitle;
    }

    public void hideKeyboardOnLaunch() {
        mShowKeyboardOnLaunch = false;
    }

    public void clickLeftButton() {
        mLeftButton.performClick();
    }

    //protected methods
    protected void setLeftButton(Button button) {
        mLeftButton = button;
        mLeftButton.setText(mLeftButtonText);
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            //Overridden
            @Override
            public void onClick(View v) {
                onLeftButtonClick();
            }
        });

    }

    protected Intent getReturnData() {
        if (mReturnData == null) {
            return createNewReturnData();
        } else {
            return mReturnData;
        }
    }

    protected void onLeftButtonClick() {
        sendResult(mLeftButtonResult);
        if (mDismissOnLeftClick) dismiss();
    }

    protected String getTitleForAction(ActionType type) {
        return type.getLabel(getActivity());
    }

    protected void dismissDialogOnLeftClick() {
        mDismissOnLeftClick = true;
    }

    protected Intent createNewReturnData() {
        mReturnData = new Intent();
        return mReturnData;
    }

    protected void sendResult(ActivityResultCode resultCode) {
        if (getTargetFragment() == null) return;
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode.get(),
                getReturnData());
        mReturnData = null;
    }

    /**
     * Returns a button layout with appropriate actions and labels.
     *
     * @param parent: by default a LinearLayout that will hold the return View followed by the
     *                buttons. If the View is inflated from an XML file using a layout inflater,
     *                "parent" should be passed as the parent ViewGroup parameter to the inflater.
     * @return View: the button UI for the Dialog.
     * @see #onCreateDialog(Bundle)
     * @see #createDialogUi()
     */
    protected View getButtons(ViewGroup parent) {
        View button = getActivity().getLayoutInflater().inflate(ONE_BUTTON_LAYOUT, parent, false);

        setLeftButton((Button) button.findViewById(BUTTON));

        return button;
    }

    protected <T> T getTargetListenerOrThrow(Class<T> listenerClass) {
        Fragment target = getTargetFragment();
        if(target != null) {
            try {
                return listenerClass.cast(target);
            } catch (ClassCastException e) {
                throw new ClassCastException("Target fragment " + target.getTag() +
                        " must implement " + listenerClass.getName());
            }
        } else {
            return getActivityListenerOrThrow(listenerClass);
        }
    }

    private <T> T getActivityListenerOrThrow(Class<T> listenerClass) {
        try {
            return listenerClass.cast(getActivity());
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement " + listenerClass.getName());
        }
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
        View dialogUi = createDialogUi();
        if (dialogUi != null) {
            layout.addView(dialogUi, lp1);
        }
        layout.addView(getButtons(layout), lp);

        if (mDialogTitle == null || mDialogTitle.length() == 0) {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftButtonAction(LEFT_BUTTON_DEFAULT_ACTION);
        dismissDialogOnLeftClick();
    }

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
}
