package com.aulaudemy.tasklist.helper;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener recyclerClickListener;
    GestureDetector recyclerGestureDetector;

    public interface OnItemClickListener extends AdapterView.OnItemClickListener {
        public void onItemClick(View view, int position );

        public void onLongItemClick( View view, int position );
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if(childView != null && recyclerClickListener != null && recyclerGestureDetector.onTouchEvent(motionEvent)) {
            recyclerClickListener.onItemClick(
                    childView,
                    recyclerView.getChildAdapterPosition(childView)
            );
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public RecyclerClickListener(Context context, final RecyclerView RECYCLERVIEW, OnItemClickListener listener) {
        recyclerClickListener = listener;
        recyclerGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                View child = RECYCLERVIEW.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(child != null && recyclerClickListener != null) {
                    recyclerClickListener.onLongItemClick(child, RECYCLERVIEW.getChildAdapterPosition(child));
                }
            }
        });
    }
}
