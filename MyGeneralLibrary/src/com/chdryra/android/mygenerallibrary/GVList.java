package com.chdryra.android.mygenerallibrary;

import android.view.View;

import com.chdryra.android.mygenerallibrary.GridViewCellAdapter.GridViewable;

public abstract class GVList<T extends GVData> extends SortableList<T> implements GridViewable<T> {
	@Override
	public abstract ViewHolder getViewHolder(View convertView);
	
	public GVList() {		
	}		
}
