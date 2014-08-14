package com.chdryra.android.mygenerallibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.TextView.OnEditorActionListener;

public class ArrayAdapterSearchView extends SearchView {
	private AutoCompleteTextView mSearchAutoComplete;

	public ArrayAdapterSearchView(Context context) {
	    super(context);
	    initialize();
	}

	public ArrayAdapterSearchView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    initialize();
	}

	public void initialize() {
		View v = inflate(getContext(), R.layout.autocomplete_search_view, null);
	    mSearchAutoComplete = (AutoCompleteTextView)v.findViewById(R.id.autocomplete_text_view);
	    setAdapter(null);
	    setOnItemClickListener(null);
	}

	@Override
	public void setSuggestionsAdapter(CursorAdapter adapter) {
	}

	public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
	    mSearchAutoComplete.setOnItemClickListener(listener);
	}

	public void setAdapter(ArrayAdapter<?> adapter) {
	    mSearchAutoComplete.setAdapter(adapter);
	}
	
	public void setText(String text) { 
		mSearchAutoComplete.setText(text); 
	}

	public String getText() {
		return mSearchAutoComplete.getText().toString();
	}

	public void setOnEditorActionListener(
			OnEditorActionListener onEditorActionListener) {
		mSearchAutoComplete.setOnEditorActionListener(onEditorActionListener);
		
	} 
}
