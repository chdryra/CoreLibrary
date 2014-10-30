/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * A standard delete confirmation alert dialog. Asks the user to confirm with a "Yes" or "Cancel".
 * <p>
 * There is a static method {@link #showDeleteConfirmDialog(String, android.app.Fragment, int,
 * android.app.FragmentManager)} that can be called to show
 * the default confirmation dialog. This is preferable to constructing the dialog yourself.
 * </p>
 */
public class DialogDeleteConfirmFragment extends DialogAlert {
    public static final  ActionType DELETE_CONFIRM     = ActionType.YES;
    private static final String     DELETE_WHAT        = "com.chdryra.android.mygenerallibrary" +
                                                         ".delete_what";
    private static final String     DELETE             = "Delete";
    private static final String     DELETE_CONFIRM_TAG = "DeleteConfirm";
    private static final ActionType DELETE_CANCEL      = ActionType.CANCEL;

    /**
     * Shows a standard delete confirm dialog. This should be used rather than the constructor.
     *
     * @param deleteWhat:      Title of dialog will be "Delete (deleteWhat)?"
     * @param targetFragment:  The fragment that will receive the answer.
     * @param requestCode:     The requestCode that the fragment issued and listens for on the
     *                         callback.
     * @param fragmentManager: The fragment manager that handles the showing of the dialog.
     */
    public static void showDeleteConfirmDialog(String deleteWhat, Fragment targetFragment,
                                               int requestCode, FragmentManager fragmentManager) {
        DialogDeleteConfirmFragment dialog = new DialogDeleteConfirmFragment();
        Bundle args = new Bundle();
        args.putString(DELETE_WHAT, deleteWhat);
        dialog.setTargetFragment(targetFragment, requestCode);
        dialog.setArguments(args);
        dialog.show(fragmentManager, DELETE_CONFIRM_TAG);
    }

    @Override
    protected String getAlertString() {
        String deleteWhat = getArguments().getString(DELETE_WHAT);
        return deleteWhat != null ? DELETE + " " + deleteWhat + "?" : DELETE + "?";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRightButtonAction(DELETE_CONFIRM);
        setLeftButtonAction(DELETE_CANCEL);
    }
}