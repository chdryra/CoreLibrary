package com.chdryra.android.mygenerallibrary;

import java.util.Comparator;

public class GVStringList extends GVList<GVString> {
	
	public GVStringList() {	
	}
	
	public void add(String string) {
		if(string != null && string.length() > 0)
			mData.add(new GVString(string));
	}
	
	public boolean contains(String string) {
		return mData.contains(new GVString(string));
	}
	
	public void remove(String string) {
		remove(new GVString(string));
	}

	@Override
	protected Comparator<GVString> getDefaultComparator() {
		return new Comparator<GVString>() {

			@Override
			public int compare(GVString lhs, GVString rhs) {
				return lhs.toString().compareTo(rhs.toString());
			}
		};
	}
}
