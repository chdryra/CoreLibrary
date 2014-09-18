package com.chdryra.android.mygenerallibrary;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class SortableList<T> implements Iterable<T> {
	protected LinkedList<T> mData = new LinkedList<T>();
	protected boolean mIsSorted = false;

	public SortableList() {
		
	}
	
	public SortableList(SortableList<T> list) {
		add(list);
	}
	
	public void add(T item) {
		mData.add(item);
		mIsSorted = false;
	}
	
	public void add(SortableList<T> list) {
		for(T item : list)
			mData.add(item);
		mIsSorted = false;
	}
	
	public void remove(T item) {
		mData.remove(item);
		mIsSorted = false;
	}

	public void remove(int position) {
		mData.remove(getItem(position));
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
	
	public int size() {
		return mData.size();
	}

	public T getItem(int position) {
		return mData.get(position);
	}
	
	public boolean isSorted() {
		return mIsSorted;
	}

	public void sort() {
		sort(getDefaultComparator());
	}

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
		return new SLIterator();
	}
	
	class SLIterator implements Iterator<T> {
		int position = 0;
		
		@Override
		public boolean hasNext() {
			return position < size() && getItem(position) != null;
		}

		@Override
		public T next() {
			if(hasNext())
				return (T)getItem(position++);
			else				
				throw new NoSuchElementException("No more elements left");
		}

		@Override
		public void remove() {
			if(position <= 0)
				throw new IllegalStateException("Have to do at least one next() before you can delete");
			else
				mData.remove(((T)getItem(position-1)));
		}
	}
}
