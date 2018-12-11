package com.idealcn.recyclerView.listener;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: guoning
 * @date: 2018/12/11 10:16
 * @description: RecyclerView的item的单击和长按事件
 */
public abstract class RecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat gestureDetectorCompat;
    private RecyclerView recyclerView;
    public RecyclerViewItemClickListener(final RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.gestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(),
                new GestureDetector.SimpleOnGestureListener(){
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        View childViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (null!=childViewUnder){
                            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childViewUnder);
                            onItemClick(childViewHolder);
                        }
                        return super.onSingleTapUp(e);
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        super.onLongPress(e);
                        View childViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (null!=childViewUnder){
                            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childViewUnder);
                            onItemLongClick(childViewHolder);
                        }
                    }
                });
    }

    protected abstract void onItemLongClick(RecyclerView.ViewHolder childViewHolder);

    protected abstract void onItemClick(RecyclerView.ViewHolder childViewHolder);

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
