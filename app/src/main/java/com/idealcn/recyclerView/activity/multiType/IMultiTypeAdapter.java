package com.idealcn.recyclerView.activity.multiType;

import android.view.ViewGroup;

/**
 * @author: guoning
 * @date: 2018/12/11 15:07
 * @description:
 */
public interface IMultiTypeAdapter {

    boolean canParseItemType(MultiTypeData data);

    MultiTypeAdapter.MultiTypeHolder onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(MultiTypeAdapter.MultiTypeHolder holder, int position, MultiTypeData data);

}
