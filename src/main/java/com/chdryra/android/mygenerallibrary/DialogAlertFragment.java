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

/**
 * Simple abstract class for building alert dialogs, for example user confirmation requests etc.
 * Default button actions are "Yes" and "No".
 */
public class DialogAlertFragment extends DialogTwoButtonFragment {
    public static final ActionType NEGATIVE_ACTION = ActionType.CANCEL;
    public static final ActionType POSITVE_ACTION  = ActionType.YES;
    public static final String     ALERT_TAG       = "com.chdryra.android.reviewer.alert_tag";

    private DialogAlertListener mListener;
    private Bundle              mArgs;

    public interface DialogAlertListener {
        public void onAlertNegative(int requestCode, Bundle args);

        public void onAlertPositive(int requestCode, Bundle args);
    }

    public static DialogAlertFragment newDialog(String alert) {
        return newDialog(alert, new Bundle());
    }

    public static DialogAlertFragment newDialog(String alert, Bundle args) {
        args.putString(ALERT_TAG, alert);
        DialogAlertFragment dialog = new DialogAlertFragment();
        dialog.setArguments(args);

        return dialog;
    }

    public void clickNegativeButton() {
        clickLeftButton();
    }

    public void clickPositiveButton() {
        clickRightButton();
    }

    /**
     * Returns null view to keep alert simply a question and 2 buttons.
     *
     * @return null
     */
    @Override
    protected final View createDialogUi() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftButtonAction(NEGATIVE_ACTION);
        setRightButtonAction(POSITVE_ACTION);
        dismissDialogOnLeftClick();
        dismissDialogOnRightClick();
        setDialogTitle(getArguments().getString(ALERT_TAG));
        hideKeyboardOnLaunch();
        mListener = getTargetListener(DialogAlertListener.class);
        mArgs = getArguments() == null ? new Bundle() : getArguments();
    }

    @Override
    protected void onLeftButtonClick() {
        mListener.onAlertNegative(getTargetRequestCode(), mArgs);
        super.onLeftButtonClick();
    }

    @Override
    protected void onRightButtonClick() {
        mListener.onAlertPositive(getTargetRequestCode(), mArgs);
        super.onRightButtonClick();
    }
}
