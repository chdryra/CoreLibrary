/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Ui;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public abstract class RecyclerAdapterBasic<T> extends android.support.v7.widget.RecyclerView.Adapter {
    private final List<T> mData;
    private final OnItemClickListener<T> mClickListener;
    private RecyclerView mRecyclerView;

    public interface OnItemClickListener<T> {
        void onItemClick(T datum, int position, View v);

        void onItemLongClick(T datum, int position, View v);
    }

    protected abstract View inflateView(ViewGroup parent, int viewType);

    protected abstract ViewHolderAbstract<T> newRecyclerViewHolder(View v, int viewType);

    public RecyclerAdapterBasic(List<T> data,
                                @Nullable OnItemClickListener<T> clickListener) {
        mData = data;
        mClickListener = clickListener;
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    private void onItemClick(View v) {
        if (mClickListener == null) return;
        int itemPosition = mRecyclerView.getChildLayoutPosition(v);
        mClickListener.onItemClick(mData.get(itemPosition), itemPosition, v);
    }

    private void onItemLongClick(View v) {
        if (mClickListener == null) return;
        int itemPosition = mRecyclerView.getChildLayoutPosition(v);
        mClickListener.onItemLongClick(mData.get(itemPosition), itemPosition, v);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = inflateView(parent, viewType);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(view);
            }
        });

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClick(view);
                return false;
            }
        });

        return newRecyclerViewHolder(v, viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderAbstract<T> vh = (ViewHolderAbstract<T>) holder; //we know the type here.
        vh.updateData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
