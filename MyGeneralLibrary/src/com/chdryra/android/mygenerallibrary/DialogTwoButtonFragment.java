package com.chdryra.android.mygenerallibrary;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.actionbarsherlock.app.SherlockDialogFragment;

public abstract class DialogTwoButtonFragment extends SherlockDialogFragment {

	protected Button mLeftButton;
	protected Button mRightButton;

	private String mLeftButtonText;
	private String mRightButtonText;

	private ActivityResultCode mLeftButtonResult;
	private ActivityResultCode mRightButtonResult;

	private boolean mDismissOnLeftClick = false;
	private boolean mDismissOnRightClick = false;

	private String mDialogTitle;
	private Intent mReturnData;

	private boolean mNoTitle = true;
	private boolean mShowKeyboardOnLaunch = true;
	
	public static enum ActionType {
		CANCEL(ActivityResultCode.CANCEL), 
		DONE(ActivityResultCode.DONE), 
		OTHER(ActivityResultCode.OTHER), 
		EDIT(ActivityResultCode.EDIT), 
		ADD(ActivityResultCode.ADD), 
		DELETE(ActivityResultCode.DELETE), 
		CLEAR(ActivityResultCode.CLEAR), 
		OK(ActivityResultCode.OK),
		YES(ActivityResultCode.YES),
		NO(ActivityResultCode.NO);
		
		private final ActivityResultCode mResultCode;

		private ActionType(ActivityResultCode resultCode) {
			this.mResultCode = resultCode;
		}

		public ActivityResultCode getResultCode() {
			return mResultCode;
		}
	}

	protected abstract View createDialogUI();

	protected void showKeyboardOnLaunch(boolean showKeyboard) {
		mShowKeyboardOnLaunch = showKeyboard;
	}
	
	protected void onLeftButtonClick() {
		sendResult(mLeftButtonResult);
	};

	protected void onRightButtonClick() {
		sendResult(mRightButtonResult);
	};

	public void setLeftButtonText(String leftButtonText) {
		mLeftButtonText = leftButtonText;
	}

	public void setRightButtonText(String rightButtonText) {
		mRightButtonText = rightButtonText;
	}

	public void setLeftButtonAction(ActionType action) {
		mLeftButtonText = getTitleForAction(action);
		mLeftButtonResult = action.getResultCode();
	}

	public void setRightButtonAction(ActionType action) {
		mRightButtonText = getTitleForAction(action);
		mRightButtonResult = action.getResultCode();
	}

	private String getTitleForAction(ActionType type) {
		if (type == ActionType.ADD)
			return getResources().getString(R.string.button_add_text);
		if (type == ActionType.CANCEL)
			return getResources().getString(R.string.button_cancel_text);
		if (type == ActionType.DELETE)
			return getResources().getString(R.string.button_delete_text);
		if (type == ActionType.DONE)
			return getResources().getString(R.string.button_done_text);
		if (type == ActionType.EDIT)
			return getResources().getString(R.string.button_edit_text);
		if (type == ActionType.CLEAR)
			return getResources().getString(R.string.button_clear_text);
		if (type == ActionType.OK)
			return getResources().getString(R.string.button_ok_text);
		if (type == ActionType.YES)
			return getResources().getString(R.string.button_yes_text);
		if (type == ActionType.NO)
			return getResources().getString(R.string.button_no_text);
		else
			return null;
	}

	public void setDialogTitle(String dialogTitle) {
		if(dialogTitle != null) {
			mDialogTitle = dialogTitle;
			mNoTitle = false;
		} else
			mNoTitle = true;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return buildDialog();
	}

	@Override
	public void onStop() {
		getSherlockActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		super.onStop();
	}

	protected void setDismissDialogOnLeftClick(boolean dismiss) {
		mDismissOnLeftClick = dismiss;
	}

	protected void setDismissDialogOnRightClick(boolean dismiss) {
		mDismissOnRightClick = dismiss;
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
	
	protected void sendResult(ActivityResultCode resultCode) {
		if (getTargetFragment() == null)
			return;

		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode.get(), getReturnData());
		mReturnData = null;
		
		if (resultCode.equals(mLeftButtonResult) && mDismissOnLeftClick)
			dismiss();

		if (resultCode.equals(mRightButtonResult) && mDismissOnRightClick)
			dismiss();
	}

	protected View getButtons() {
		View buttons = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_two_button_layout, null);

		mLeftButton = (Button) buttons.findViewById(R.id.button_left);
		mRightButton = (Button) buttons.findViewById(R.id.button_right);

		mLeftButton.setText(mLeftButtonText);
		mLeftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onLeftButtonClick();
			}
		});

		mRightButton.setText(mRightButtonText);
		mRightButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onRightButtonClick();
			}
		});

		return buttons;
	}

	protected Dialog buildDialog() {
		Dialog dialog = new Dialog(getActivity());

		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);
		
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(lp);

		//Hacky layout params to get listview dialogUIs to render properly. 
		//Need to set layout weight of 1 on it...
		if(createDialogUI() != null)
			layout.addView(createDialogUI(), lp1);
		layout.addView(getButtons(), lp);

		if(mNoTitle)
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		dialog.setContentView(layout);
		dialog.setTitle(mDialogTitle);
		
		if(mShowKeyboardOnLaunch)
			dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

		return dialog;
	}
}
