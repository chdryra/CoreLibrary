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
 */
public abstract class DialogAlert extends DialogTwoButtonFragment {
    private final ActionType mActionRight = ActionType.YES;
    private final ActionType mActionLeft  = ActionType.NO;

    protected abstract String getAlertString();

    @Override
    protected final View createDialogUI(ViewGroup parent) {
        return null;
    }

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
