/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;

/**
 * A standard delete confirmation alert dialog. Asks the user to confirm with a "Yes" or "Cancel".
 * <p>
 * There is a static method {@link #showDialog(String, android.app.Fragment, int,
 * android.app.FragmentManager)} that can be called to show
 * the default confirmation dialog. This is preferable to constructing the dialog yourself.
 * </p>
 */
public class DialogDeleteConfirm {
    public static final DialogTwoButtonFragment.ActionType DELETE_CONFIRM = DialogAlertFragment
            .POSITVE_ACTION;
    public static final DialogTwoButtonFragment.ActionType DELETE_CANCEL = DialogAlertFragment
            .NEGATIVE_ACTION;

    public static final String DELETE_CONFIRM_TAG = "DeleteConfirm";
    private static final String DELETE = "Delete";

    private DialogDeleteConfirm() {

    }

//Static methods
    /**
     * Shows a standard delete confirm dialog.
     *
     * @param deleteWhat:      Title of dialog will be "Delete (deleteWhat)?"
     * @param targetFragment:  The fragment that will receive the answer. Needs to implement
     *                         {@link com.chdryra.android.mygenerallibrary.DialogAlertFragment
     *                         .DialogAlertListener}.
     * @param requestCode:     The requestCode that the fragment issued and listens for on the
     *                         callback.
     * @param fragmentManager: The fragment manager that handles the showing of the dialog.
     */
    public static void showDialog(String deleteWhat, Fragment targetFragment, int requestCode,
                                  FragmentManager fragmentManager) {
        String alert = deleteWhat != null ? DELETE + " " + deleteWhat + "?" : DELETE + "?";
        DialogFragment dialog = DialogAlertFragment.newDialog(alert);
        dialog.setTargetFragment(targetFragment, requestCode);
        dialog.show(fragmentManager, DELETE_CONFIRM_TAG);
    }
}
