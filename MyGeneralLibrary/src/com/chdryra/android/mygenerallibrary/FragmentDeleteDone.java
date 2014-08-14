package com.chdryra.android.mygenerallibrary;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDeleteDone extends DialogFragment {

	private static final int DELETE_CONFIRM = 0;

	private boolean mDismissOnDone = true;
	private boolean mDismissOnDelete = false;
	private boolean mDeleteConfirmation = true;
	private String mDeleteWhat;
	private Intent mReturnData;
	private boolean mDisplayHomeAsUp = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(mDisplayHomeAsUp);
		return v;
	}

	protected void setDisplayHomeAsUp(boolean displayHomeAsUp) {
		mDisplayHomeAsUp = displayHomeAsUp;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case DELETE_CONFIRM:
			if(DialogDeleteConfirmFragment.DELETE_CONFIRM.getResultCode().equals(resultCode))
				doDeleteSelected();
			break;
		default:
			break;
		}
	}

	protected void sendResult(ActivityResultCode resultCode) {
		getActivity().setResult(resultCode.get(), getReturnData());
	}

	protected Intent getNewReturnData() {
		mReturnData = new Intent();
		return mReturnData;
	}
	
	protected Intent getReturnData() {
		if(mReturnData == null)
			return getNewReturnData();
		else
			return mReturnData;
	}
	protected void setDeleteWhatTitle(String deleteWhat) {
		mDeleteWhat = deleteWhat;
	}

	protected void setDeleteConfirmation(boolean deleteConfirmation) {
		mDeleteConfirmation = deleteConfirmation;
	}

	protected boolean hasDataToDelete() {
		return false;
	}
	
	protected void onDeleteSelected() {
	}

	protected void onDoneSelected() {
	}

	protected void onUpSelected() {
	}

	protected void setDismissOnDone(boolean dismiss) {
		mDismissOnDone = dismiss;
	}
	
	protected void setDismissOnDelete(boolean dismiss) {
		mDismissOnDelete = dismiss;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, android.view.MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_delete_done, menu);
	}
	
	private void showDeleteConfirmDialog() {
		DialogDeleteConfirmFragment.showDeleteConfirmDialog(mDeleteWhat,
				FragmentDeleteDone.this, DELETE_CONFIRM, getFragmentManager());
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
			doUpSelected();
			return true;
		} else if (itemId == R.id.menu_item_delete) {
			if (hasDataToDelete() && mDeleteConfirmation)
				showDeleteConfirmDialog();
			else
				doDeleteSelected();
			return true;
		} else if (itemId == R.id.menu_item_done) {
			doDoneSelected();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void doDeleteSelected() {
		if (hasDataToDelete()) {
			onDeleteSelected();
			sendResult(ActivityResultCode.DELETE);
			if(mDismissOnDelete)
				getActivity().finish();
		}
	}
	
	private void doDoneSelected() {
		onDoneSelected();
		sendResult(ActivityResultCode.DONE);
		if(mDismissOnDone)
			getActivity().finish();
	}
	
	private void doUpSelected() {
		onUpSelected();
		sendResult(ActivityResultCode.UP);
		getActivity().finish();
	}
}
