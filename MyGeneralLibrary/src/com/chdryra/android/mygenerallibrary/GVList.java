package com.chdryra.android.mygenerallibrary;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import android.view.View;

import com.chdryra.android.mygenerallibrary.GridViewCellAdapter.GridViewable;


public abstract class GVList<T extends GVData> implements GridViewable<T> {
	protected LinkedList<T> mData = new LinkedList<T>();
	protected boolean mIsSorted = false;

	@Override
	public abstract ViewHolder getViewHolder(View convertView);
	
	public void add(T data) {
		mData.add(data);
		mIsSorted = false;
	}
	
	public void add(GVList<T> list) {
		for(T comment : list)
			mData.add(comment);
		mIsSorted = false;
	}
	
	public void remove(T comment) {
		mData.remove(comment);
		mIsSorted = false;
	}
	
	public void removeAll() {
		mData.clear();
	}
	
	public boolean contains(T item) {
		return mData.contains(item);
	}
	
	public int indexOf(T item) {
		return mData.indexOf(item);
	}
	
	@Override
	public int size() {
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}
	
	@Override
	public boolean isSorted() {
		return mIsSorted;
	}

	@Override
	public void sort() {
		sort(getDefaultComparator());
	}

	@Override
	public void sort(Comparator<T> comparator) {
		if(!isSorted())
			Collections.sort(mData, comparator);
		mIsSorted = true;
	}
	
	protected Comparator<T> getDefaultComparator() {
		return new Comparator<T>() {
			@Override
			public int compare(T lhs, T rhs) {
				if(mData.contains(lhs) && mData.contains(rhs))
					return mData.indexOf(lhs) - mData.indexOf(rhs);
				else
					return 0;
			}
		};
	}
	
	@Override
	public Iterator<T> iterator() {
		return new GVIterator();
	}
	
	class GVIterator implements Iterator<T> {
		int position = 0;
		
		@Override
		public boolean hasNext() {
			return position < size() && getItem(position) != null;
		}

		@Override
		public T next() {
			if(hasNext())
				return (T)getItem(position++);
			else				throw new NoSuchElementException("No more elements left");
		}

		@Override
		public void remove() {
			if(position <= 0) {
				throw new IllegalStateException("Have to do at least one next() before you can delete");
			} else
				mData.remove(((T)getItem(position-1)));
		}
	}
}
