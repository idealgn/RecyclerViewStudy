package com.idealcn.recyclerView.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Map;

/**
 * Created by ideal-gn on 2017/8/29.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "handler";
    private Paint paint;
    private Paint textPaint;
    private Paint dividerPaint;
    private Map<Integer, String> dataList;
    private int top ;
    private int left ;
    private Context context;

    private int dp2px(int dp){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (displayMetrics.scaledDensity * dp + 0.5f);
    }

    public MyItemDecoration(Context context,Map<Integer, String> dataList) {
        this.dataList = dataList;
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#99000000"));
        paint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(this.context.getResources().getDisplayMetrics().scaledDensity*16+0.5f);
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setFakeBoldText(true);
        textPaint.setStrokeWidth(dp2px(3));


        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setColor(Color.parseColor("#c13ae6"));
        dividerPaint.setStyle(Paint.Style.FILL);
        dividerPaint.setStrokeWidth(dp2px(3));

        top = dp2px(30);
        left = dp2px(15);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        Rect rect;
        int childCount = parent.getChildCount();
        for (int x = 0; x < childCount; x++) {
            View child = parent.getChildAt(x);

            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int childAdapterPosition = parent.getChildAdapterPosition(child);
            String text = dataList.get(childAdapterPosition);
            if (TextUtils.isEmpty(text)) continue;

            rect = new Rect();


            c.drawRect(0,
                    child.getTop() - lp.topMargin - top,
                    parent.getWidth(),
                    child.getTop(),
                    dividerPaint);


            textPaint.getTextBounds(text, 0, text.length(), rect);
            c.drawText(text,
                    left,
                    child.getTop() - top - lp.topMargin + top / 2 + (rect.bottom - rect.top) / 2,
                    textPaint
            );

        }


    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);


        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        View child = parent.findViewHolderForAdapterPosition(firstVisibleItemPosition).itemView;
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();

        Rect rect = new Rect();
        final int floatingTitleHeight = top + lp.topMargin;
        rect.set(0, 0, parent.getWidth(), floatingTitleHeight);
        c.drawRect(rect, paint);


        String text = get(firstVisibleItemPosition);
        if (TextUtils.isEmpty(text)) return;
        Rect textRect = new Rect();
        paint.getTextBounds(text, 0, text.length(), textRect);

        c.drawText(text,
                left,
                 floatingTitleHeight / 2 + (textRect.bottom - textRect.top) / 2,//这里仍有问题
                textPaint);

    }

    //这个方法很机智,五星好评!!!!!
    private String get(int index) {
        while (index>=0){
            if (dataList.containsKey(index))
                return dataList.get(index);
            index--;
        }
        return null;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (dataList.containsKey(childAdapterPosition))
            //这里设置的其实是某个方向上区域的高度,而不是坐标,outRect相当于一个数组存储这些高度
            outRect.top = top;


    }


}
