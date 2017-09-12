package com.idealcn.recyclerView.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ideal-gn on 2017/9/12.
 *
 * 参考博客: http://www.jianshu.com/p/5cb27a2ce03d
 */

public class SlideDeleteLayout extends ViewGroup  {

    private View mContent; // 内容部分
    private View mDelete;  // 删除部分
    private ViewDragHelper helper;
    private int mContentWidth,mContentHeight;
    private int mDeleteWidth;
    private int mDeleteHeight;


    public SlideDeleteLayout(Context context) {
        super(context);
    }

    public SlideDeleteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideDeleteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public MarginLayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContent = getChildAt(0);
        mDelete = getChildAt(1);
        helper = ViewDragHelper.create(this, 1, new SimpleCallBack());
    }
    private class SimpleCallBack extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child==mContent || child==mDelete;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //解决左右滑动越界
            if(child == mContent){ // 解决内容部分左右拖动的越界问题
                if(left>0){//右滑
                    return 0;
                }else if(-left>mDeleteWidth){//左滑
                    return -mDeleteWidth;
                }
            }

            if(child == mDelete){ // 解决删除部分左右拖动的越界问题
                if(left<mContentWidth - mDeleteWidth){
                    return mContentWidth - mDeleteWidth;
                }else if(left > mContentWidth){
                    return mContentWidth;
                }
            }

            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            invalidate();
            if (changedView==mContent){
                int mTempDeleteLeft = mContentWidth + left;
                mDelete.layout(mTempDeleteLeft,top,mTempDeleteLeft+mDeleteWidth,top+mDeleteHeight);
            }
            if (changedView==mDelete){
                mContent.layout(left-mContentWidth,top,left,top+mContentHeight);
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int left = mContent.getLeft();
            if (-left>mDeleteWidth/2){
                isShowDelete(true);
                if (listener!=null){
                    listener.onOpen(SlideDeleteLayout.this);
                }
            }else {
                isShowDelete(false);
                if (listener!=null) {
                    listener.onClose(SlideDeleteLayout.this);
                }
            }


            super.onViewReleased(releasedChild, xvel, yvel);
        }
    }

    public void isShowDelete(boolean isShowDelete){
        if (isShowDelete){
            helper.smoothSlideViewTo(mContent,-mDeleteWidth,0);
            helper.smoothSlideViewTo(mDelete,mContentWidth - mDeleteWidth,0);
        }else {

            helper.smoothSlideViewTo(mDelete,mContentWidth,0);
            helper.smoothSlideViewTo(mContent,0,0);
        }
        invalidate();
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (helper.continueSettling(true))
            invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 这跟mContent的父亲的大小有关，父亲是宽填充父窗体，高度是和孩子一样是60dp
        mContent.measure(widthMeasureSpec,heightMeasureSpec); // 测量内容部分的大小
        LayoutParams layoutParams = mDelete.getLayoutParams();
        int deleteWidth = MeasureSpec.makeMeasureSpec(layoutParams.width,MeasureSpec.EXACTLY);
        int deleteHeight = MeasureSpec.makeMeasureSpec(layoutParams.height,MeasureSpec.EXACTLY);
        // 这个参数就需要指定为精确大小
        mDelete.measure(deleteWidth,deleteHeight); // 测量删除部分的大小
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
         mContentWidth = mContent.getMeasuredWidth();
        mDeleteHeight =   mContentHeight = mContent.getMeasuredHeight();
        mContent.layout(0,0,mContentWidth,mContentHeight); // 摆放内容部分的位置
         mDeleteWidth = mDelete.getMeasuredWidth();
        mDelete.layout(mContentWidth,0,
                mContentWidth + mDeleteWidth, mContentHeight); // 摆放删除部分的位置
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        helper.processTouchEvent(event);
        return true;
    }

    private OnSlideDeleteListener listener;
    // SlideDlete的接口
    public interface OnSlideDeleteListener {
        void onOpen(SlideDeleteLayout slideDelete);
        void onClose(SlideDeleteLayout slideDelete);
    }

    public void setListener(OnSlideDeleteListener listener) {
        this.listener = listener;
    }
}
