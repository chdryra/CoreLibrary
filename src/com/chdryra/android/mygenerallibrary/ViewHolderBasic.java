/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public abstract class ViewHolderBasic implements ViewHolder {
	private int mLayout;
	protected View mInflated;
	protected VHInflater mInflater;
	
	@Override
	public abstract View updateView(GVData data);
	protected abstract void initViewsToUpdate();
	
	protected ViewHolderBasic(int layout) {
		mLayout = layout;
		mInflater = getDefaultInflater();
	}
	
	protected ViewHolderBasic(int layout, VHInflater inflater) {
		mLayout = layout;
		mInflater = inflater;
	}
	
	public void setInflater(VHInflater inflater) {
		mInflater = inflater;
	}
	
	@Override
	public View inflate(Activity activity, ViewGroup parent) {
		mInflated = mInflater.inflate(mLayout, activity, parent);
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
	
	protected VHInflater getDefaultInflater() {
		return new VHInflater() {
			@Override
			public View inflate(int layoutId, Activity activity, ViewGroup parent) {
				return activity.getLayoutInflater().inflate(mLayout, parent, false);
			}
		};
	}

	public interface VHInflater {
		public View inflate(int layoutId, Activity activity, ViewGroup parent);
	}

}
