package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;


public interface ViewHolder {
	public View inflate(Activity activity, ViewGroup parent);
	public View updateView(GVData data);
}
