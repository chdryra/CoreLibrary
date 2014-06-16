package com.chdryra.android.mygenerallibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

public class DialogDeleteConfirmFragment extends DialogTwoButtonFragment {
	public static final String DELETE_WHAT = "com.chdryra.android.mygenerallibrary.delete_what";
	public static final ActionType DELETE_CONFIRM = ActionType.YES;
	public static final ActionType DELETE_CANCEL = ActionType.NO;
	
	private static final String DELETE = "Delete";
	private static final String DELETE_CONFIRM_TAG = "DeleteConfirm";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRightButtonAction(DELETE_CONFIRM);
		setDismissDialogOnRightClick(true);
		setLeftButtonAction(DELETE_CANCEL);
		setDismissDialogOnLeftClick(true);
		
		String deleteWhat = getArguments().getString(DELETE_WHAT);
		String title = deleteWhat != null? DELETE + " " + deleteWhat + "?" : DELETE + "?";
		setDialogTitle(title);
		showKeyboardOnLaunch(false);
	}
	
	@Override
	protected View createDialogUI() {
		return null;
	}
	
	public static void showDeleteConfirmDialog(String deleteWhat, Fragment targetFragment, int requestCode, FragmentManager fragmentManager) {
		DialogDeleteConfirmFragment dialog = new DialogDeleteConfirmFragment();
		Bundle args = new Bundle();
		args.putString(DELETE_WHAT, deleteWhat);
		dialog.setTargetFragment(targetFragment, requestCode);
		dialog.setArguments(args);
		dialog.show(fragmentManager, DELETE_CONFIRM_TAG);
	}
}
