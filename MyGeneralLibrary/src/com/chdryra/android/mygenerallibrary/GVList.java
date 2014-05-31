package com.chdryra.android.mygenerallibrary;

import com.chdryra.android.mygenerallibrary.GridViewCellAdapter.GridViewable;

public abstract class GVList<T extends GVData> extends SortableList<T> implements GridViewable<T> {
	@Override
	public abstract ViewHolder getViewHolder(int position);
	
	public GVList() {}		
}
