package com.chdryra.android.mygenerallibrary;

import android.view.View;

public interface GridViewableData {
	public int size();
	public Object getItem(int position);
	public ViewHolder getViewHolder(View convertView);
}
