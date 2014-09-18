package com.chdryra.android.mygenerallibrary;

import android.view.View;
import android.widget.Button;

public abstract class DialogThreeButtonFragment extends DialogTwoButtonFragment {
	protected Button mMiddleButton;
	private String mMiddleButtonText;
	private ActivityResultCode mMiddleButtonResult;
	private boolean mDismissOnMiddleClick = false;

	protected abstract View createDialogUI();


	protected void onMiddleButtonClick() {
		sendResult(mMiddleButtonResult);
	};

	public void setMiddleButtonText(String middleButtonText) {
		mMiddleButtonText = middleButtonText;
	}

	public void setMiddleButtonAction(ActionType action) {
		mMiddleButtonText = getTitleForAction(action);
		mMiddleButtonResult = action.getResultCode();
	}


	protected void setDismissDialogOnMiddleClick(boolean dismiss) {
		mDismissOnMiddleClick = dismiss;
	}
	
	protected void sendResult(ActivityResultCode resultCode) {
		super.sendResult(resultCode);
		if (resultCode.equals(mMiddleButtonResult) && mDismissOnMiddleClick)
			dismiss();
	}

	protected View getButtons() {
		View buttons = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_three_button_layout, null);

		mLeftButton = (Button) buttons.findViewById(R.id.button_left);
		mMiddleButton = (Button) buttons.findViewById(R.id.button_middle);
		mRightButton = (Button) buttons.findViewById(R.id.button_right);

		mLeftButton.setText(mLeftButtonText);
		mLeftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onLeftButtonClick();
			}
		});

		mMiddleButton.setText(mMiddleButtonText);
		mMiddleButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onMiddleButtonClick();
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
}