/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Dialogs;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.Nullable;

/**
 * A standard delete confirmation alert dialog. Asks the user to confirm with a "Yes" or "Cancel".
 * <p>
 * There is a static method {@link #showDialog(String, android.app.Fragment, int,
 * android.app.FragmentManager)} that can be called to show
 * the default confirmation dialog. This is preferable to constructing the dialog yourself.
 * </p>
 */
public class DialogDeleteConfirm {
    private static final String DELETE_CONFIRM_TAG = "DeleteConfirm";
    private static final String DELETE = "Delete";

    private DialogDeleteConfirm() {

    }

    /**
     * Shows a standard delete confirm dialog.
     *
     * @param deleteWhat:      Title of dialog will be "Delete (deleteWhat)?"
     * @param targetFragment:  The fragment that will receive the answer. Needs to implement
     *                         {@link DialogAlertFragment
     *                         .DialogAlertListener}.
     * @param requestCode:     The requestCode that the fragment issued and listens for on the
     *                         callback.
     * @param fragmentManager: The fragment manager that handles the showing of the dialog.
     */
    public static void showDialog(String deleteWhat,
                                  @Nullable Fragment targetFragment,
                                  int requestCode,
                                  FragmentManager fragmentManager) {
        String alert = deleteWhat != null ? DELETE + " " + deleteWhat + "?" : DELETE + "?";
        DialogFragment dialog = DialogAlertFragment.newDialog(alert, targetFragment, requestCode);
        dialog.show(fragmentManager, DELETE_CONFIRM_TAG);
    }

    public static void showDialog(String deleteWhat, int requestCode, FragmentManager fragmentManager) {
        showDialog(deleteWhat, null, requestCode, fragmentManager);
    }
}
