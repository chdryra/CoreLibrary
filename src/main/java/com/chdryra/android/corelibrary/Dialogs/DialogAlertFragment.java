/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Dialogs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Simple abstract class for building alert dialogs, for example user confirmation requests etc.
 * Default button actions are "Yes" and "No".
 */
public class DialogAlertFragment extends DialogTwoButtonFragment {
    public static final ActionType NEGATIVE_ACTION = ActionType.CANCEL;
    public static final ActionType POSITVE_ACTION = ActionType.YES;
    public static final String ALERT_TAG = "com.chdryra.android.reviewer.alert_tag";

    private AlertListener mListener;
    private Bundle mArgs;
    private int mRequestCode;

    public static DialogAlertFragment newDialog(String alert, @Nullable Fragment targetFragment,
                                                int requestCode) {
        return newDialog(alert, targetFragment, requestCode, new Bundle());
    }

    public static DialogAlertFragment newDialog(String alert, int requestCode, Bundle args) {
        return newDialog(alert, null, requestCode, args);
    }

    public static DialogAlertFragment newDialog(String alert, @Nullable Fragment targetFragment,
                                                int requestCode, Bundle args) {
        args.putString(ALERT_TAG, alert);
        DialogAlertFragment dialog = new DialogAlertFragment();
        dialog.setArguments(args);
        dialog.setTargetFragment(targetFragment, requestCode);
        return dialog;
    }

    public void clickNegativeButton() {
        clickLeftButton();
    }

    public void clickPositiveButton() {
        clickRightButton();
    }

    protected AlertListener getTargetListener() {
        return getTargetListenerOrThrow(AlertListener.class);
    }

    protected int getRequestCode() {
        return mRequestCode;
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
    protected void onLeftButtonClick() {
        if (mListener != null) mListener.onAlertNegative(mRequestCode, mArgs);
        super.onLeftButtonClick();
    }

    @Override
    protected void onRightButtonClick() {
        if (mListener != null) mListener.onAlertPositive(mRequestCode, mArgs);
        super.onRightButtonClick();
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
        mArgs = getArguments();
        mRequestCode = getTargetRequestCode();
        setListener(getTargetListener());
    }

    private void setListener(AlertListener listener) {
        mListener = listener;
    }
}
