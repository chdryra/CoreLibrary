package com.chdryra.android.mygenerallibrary;

import com.chdryra.android.mygenerallibrary.GridViewCellAdapter.GridViewable;

public abstract class GVList<T extends GVData> extends SortableList<T> implements GridViewable<T> {
	
	@Override
	public ViewHolder getViewHolder(int position) {
		return getItem(position).getViewHolder();
	}
}
