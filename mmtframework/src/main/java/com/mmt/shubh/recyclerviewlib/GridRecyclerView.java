package com.mmt.shubh.recyclerviewlib;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Subham Tyagi,
 * on 10/Jul/2015,
 * 11:53 AM
 * TODO:Add class comment.
 */
public class GridRecyclerView extends RecyclerView {

    private ItemClickSupport mItemClickSupport;

    public GridRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public GridRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GridRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        if (isInEditMode()) {
            return;
        }
        mItemClickSupport = ItemClickSupport.addTo(this);
        setLayoutManager(new GridLayoutManager(context, 5));
        setItemAnimator(new DefaultItemAnimator());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mItemClickSupport.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        mItemClickSupport.setOnItemLongClickListener(longClickListener);
    }

    public interface OnItemClickListener extends ListRecyclerView.OnItemClickListener {

    }

    public interface OnItemLongClickListener extends ListRecyclerView.OnItemLongClickListener {

    }
}