package com.chdryra.android.mygenerallibrary;

import java.util.Comparator;

import android.view.View;
import android.widget.TextView;

public class GVStringList extends GVList<GVStringList.GVString> {
	
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
	public ViewHolder getViewHolder(View convertView) {
		return new VHTextView(convertView);
	}

	class VHTextView implements ViewHolder {
		private TextView mTextView;
		
		public VHTextView(View convertView) {
			mTextView = (TextView)convertView.findViewById(R.id.text_view);
		}
		
		@Override
		public void updateView(Object data) {
			mTextView.setText(((GVString)data).toString());
		}
	}

	public class GVString implements GVData{
		String mString;
		
		public GVString(String string) {
			mString = string;
		}

		public String toString() {
			return mString;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((mString == null) ? 0 : mString.hashCode());
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
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (mString == null) {
				if (other.mString != null)
					return false;
			} else if (!mString.equals(other.mString))
				return false;
			return true;
		}

		private GVStringList getOuterType() {
			return GVStringList.this;
		}
	}

	@Override
	protected Comparator<GVString> getDefaultComparator() {
		return new Comparator<GVStringList.GVString>() {

			@Override
			public int compare(GVString lhs, GVString rhs) {
				return lhs.toString().compareTo(rhs.toString());
			}
		};
	}
}
