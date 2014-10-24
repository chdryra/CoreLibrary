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
 * Need to override {@link #getAlertString()} to specify the question to the user. Default
 * button actions are "Yes" and "No".
 */
public abstract class DialogAlert extends DialogTwoButtonFragment {

    protected abstract String getAlertString();

    /**
     * Returns null view to keep alert simply a question and 2 buttons.
     *
     * @param parent: irrelevant in this case
     * @return null
     */
    @Override
    protected final View createDialogUI(ViewGroup parent) {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRightButtonAction(ActionType.YES);
        setLeftButtonAction(ActionType.NO);
        setDialogTitle(getAlertString());
        dismissDialogOnRightClick();
        dismissDialogOnLeftClick();
        hideKeyboardOnLaunch();
    }
}
