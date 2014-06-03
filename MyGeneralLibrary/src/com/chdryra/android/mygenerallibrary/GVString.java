package com.chdryra.android.mygenerallibrary;

public class GVString implements GVData{
	String mString;
	
	public GVString(String string) {
		mString = string;
	}

	public String toString() {
		return mString;
	}

	@Override
	public ViewHolder getViewHolder() {
		return new VHStringView();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mString == null) ? 0 : mString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GVString other = (GVString) obj;
		if (mString == null) {
			if (other.mString != null)
				return false;
		} else if (!mString.equals(other.mString))
			return false;
		return true;
	}
}


