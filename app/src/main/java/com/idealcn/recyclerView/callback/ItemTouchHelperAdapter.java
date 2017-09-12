package com.idealcn.recyclerView.callback;

/**
 * Created by ideal-gn on 2017/9/6.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int from,int to);
    void  onItemDismiss(int position);
}
