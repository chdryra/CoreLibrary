/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.chdryra.android.corelibrary.R;

/**
 * EditText with a small 'x' for clearing. Android doesn't have a built in one.
 */
public class ClearableEditText extends EditText {

    private final Drawable mCloseButton = getResources().getDrawable(R.drawable
            .dialog_ic_close_normal_holo_light);
    private boolean mIsClearable = true;

//Constructors
    public ClearableEditText(Context context) {
        super(context);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

//public methods
    //For LibrariesTest
    public boolean isClearButtonVisible() {
        return getCompoundDrawables()[2] != null;
    }

    public void makeClearable(boolean makeClearable) {
        mIsClearable = makeClearable;
        if (!mIsClearable) {
            hideClearButton();
        }
    }

    private void init() {

        // Set bounds of the Clear button so it will look ok
        mCloseButton.setBounds(0, 0, mCloseButton.getIntrinsicWidth(),
                mCloseButton.getIntrinsicHeight());

        setOnTouchListener(new OnTouchListener() {
//Overridden
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }

                final ClearableEditText et = (ClearableEditText) v;
                et.setCursorVisible(true);
                et.handleClearButton();

                if (et.getCompoundDrawables()[2] != null &&
                        event.getX() > et.getWidth() - et.getPaddingRight() - mCloseButton
                                .getIntrinsicWidth()) {
                    et.setText("");
                    et.handleClearButton();
                }

                return false;
            }
        });

        resetOnClickListener();

        addTextChangedListener(new TextWatcher() {
//Overridden
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handleClearButton();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        setOnFocusChangeListener(new OnFocusChangeListener() {

//Overridden
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideClearButton();
                }
            }
        });

        handleClearButton();
    }

    private void resetOnClickListener() {
        setOnClickListener(new OnClickListener() {
            //Overridden
            @Override
            public void onClick(View v) {
                ClearableEditText et = (ClearableEditText) v;
                String text = et.getText().toString();
                if (text.length() > 0) {
                    et.setSelection(text.length());
                    clearOnClickListener();
                }
            }
        });
    }

    private void clearOnClickListener() {
        setOnClickListener(null);
    }

    private void handleClearButton() {
        if (this.getText().toString().equals("")) {
            hideClearButton();
        } else {
            showClearButton();
        }
    }

    private void hideClearButton() {
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], null,
                getCompoundDrawables()[3]);
    }

    private void showClearButton() {
        if (mIsClearable) {
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                    mCloseButton, getCompoundDrawables()[3]);
        }
    }

    private void hideChrome() {
        hideClearButton();
        hideKeyboard();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindowToken(), 0);
        setCursorVisible(false);
    }

//Overridden
    @Override
    public void onEditorAction(int actionCode) {
        if (actionCode == EditorInfo.IME_ACTION_DONE) {
            resetOnClickListener();
            hideChrome();
        }

        super.onEditorAction(actionCode);
    }
}