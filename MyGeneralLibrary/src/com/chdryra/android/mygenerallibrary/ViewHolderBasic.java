package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public abstract class ViewHolderBasic implements ViewHolder {
	private int mLayout;
	protected View mInflated;
	
	protected ViewHolderBasic(int layout) {
		mLayout = layout;
	}
	
	@Override
	public View inflate(Activity activity, ViewGroup parent) {
		mInflated = activity.getLayoutInflater().inflate(mLayout, parent, false);
		mInflated.setTag(this);
		initViewsToUpdate();
		return mInflated;
	}

	public View getView() {
		return mInflated;
	}
	
	protected View getView(int viewID) {
		return mInflated != null? mInflated.findViewById(viewID) : null;
	}
	
	@Override
	public abstract View updateView(GVData data);
	protected abstract void initViewsToUpdate();
}
