package com.idealcn.recyclerView.treelist.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author: guoning
 * @date: 2018/12/11 15:07
 * @description:
 */
public interface IMultiTypeAdapter<T extends TreeNode, VH extends RecyclerView.ViewHolder> {

    boolean canParseItemType(T data);

    /**
     * 在这里处理布局中的单个view的点击事件
     * @param parent
     * @return
     */
    VH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(VH holder, int position, T data);

}
