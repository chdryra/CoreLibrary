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

/**
 * Simple abstract class for building alert dialogs, for example user confirmation requests etc.
 * Need to override <code>getAlertString()</code> to specify the question to the user. Default
 * button actions are "Yes" and "No".
 *
 * @see DialogDeleteConfirmFragment
 * @see DialogTwoButtonFragment
 */
public abstract class DialogAlert extends DialogTwoButtonFragment {
    private final ActionType mActionRight = ActionType.YES;
    private final ActionType mActionLeft  = ActionType.NO;

    /**
     * Override this in a subclass to specify the alert that a user needs to attend to.
     *
     * @return String: the alert.
     */
    protected abstract String getAlertString();

    /**
     * Overrides super method: shows no UI. Only the alert and buttons will be shown.
     *
     * @param parent: ignored
     * @return View: null View returned.
     */
    @Override
    protected final View createDialogUI(ViewGroup parent) {
        return null;
    }

    /**
     * Overrides super method: shows a DialogTwoButton to show "Yes" and "No" and dismisses
     * dialog on click. Title is the alert returned by <code>getAlertString()</code>. Can
     * override this method to define different left/right button actions using
     * <code>setLeftButtonAction(ActionType action)</code> and <code>setRightButtonAction
     * (ActionType action)</code>
     * <p>
     *     If overridden, this version should be called first.
     * </p>
     * @param savedInstanceState
     *
     * @see #getAlertString()
     * @see DialogTwoButtonFragment.ActionType
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRightButtonAction(mActionRight);
        setLeftButtonAction(mActionLeft);
        setDialogTitle(getAlertString());
        dismissDialogOnRightClick();
        dismissDialogOnLeftClick();
        hideKeyboardOnLaunch();
    }
}
