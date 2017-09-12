package com.idealcn.recyclerView.decoration;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Map;

/**
 * Created by ideal-gn on 2017/9/3.
 *
 *
 * 实现分割线向右偏移
 *
 */

public class LeftItemDecoration extends RecyclerView.ItemDecoration {

    private  int left;
    private final int bottom = 3;
    private final  int top ;
    private Paint mDividerPaint;
    private Map<Integer, String> dataList;
    private Context context;

    private int dp2px(int dp){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (displayMetrics.scaledDensity * dp + 0.5f);
    }



    public LeftItemDecoration(Context context,Map<Integer, String> dataList){
        this.dataList = dataList;
        this.context = context;
        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setStyle(Paint.Style.FILL);
        mDividerPaint.setColor(Color.parseColor("#93000000"));
        mDividerPaint.setStrokeWidth(dp2px(2));

        left = dp2px(10);
        top = dp2px(2);
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = top;
//        outRect.left = left;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();

        for (int x = 0; x < childCount; x++) {
            View child = parent.getChildAt(x);
            int childAdapterPosition = parent.getChildAdapterPosition(child);
            if (dataList.containsKey(childAdapterPosition))continue;
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            c.drawRect(left,
                    child.getTop()- lp.topMargin - top,
                    parent.getWidth(),
                    child.getTop()- lp.topMargin ,
                    mDividerPaint);
        }
    }
}
