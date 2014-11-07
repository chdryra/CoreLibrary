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
 * Need to override {@link #getAlertString()} to specify the question to the user. Default
 * button actions are "Yes" and "No".
 */
public abstract class DialogAlertFragment extends DialogTwoButtonFragment {
    public static final ActionType NEGATIVE_ACTION = ActionType.NO;
    public static final ActionType POSITVE_ACTION  = ActionType.YES;

    protected abstract String getAlertString();

    /**
     * Returns null view to keep alert simply a question and 2 buttons.
     *
     * @return null
     */
    @Override
    protected final View createDialogUI() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLeftButtonAction(NEGATIVE_ACTION);
        setRightButtonAction(POSITVE_ACTION);
        dismissDialogOnLeftClick();
        dismissDialogOnRightClick();
        setDialogTitle(getAlertString());
        hideKeyboardOnLaunch();
    }
}
