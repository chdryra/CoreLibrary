package com.chdryra.android.mygenerallibrary;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class DialogDeleteConfirmFragment extends DialogAlert {
	public static final String DELETE_WHAT = "com.chdryra.android.mygenerallibrary.delete_what";
	public static final ActionType DELETE_CONFIRM = ActionType.YES;
	public static final ActionType DELETE_CANCEL = ActionType.NO;
	
	private static final String DELETE = "Delete";
	private static final String DELETE_CONFIRM_TAG = "DeleteConfirm";
		
	public static void showDeleteConfirmDialog(String deleteWhat, Fragment targetFragment, int requestCode, FragmentManager fragmentManager) {
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
		String alert = deleteWhat != null? DELETE + " " + deleteWhat + "?" : DELETE + "?";
		
		return alert;
	}
}
