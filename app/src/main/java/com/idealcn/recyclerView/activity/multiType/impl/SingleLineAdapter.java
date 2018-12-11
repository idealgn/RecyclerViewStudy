package com.idealcn.recyclerView.activity.multiType.impl;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.activity.multiType.IMultiTypeAdapter;
import com.idealcn.recyclerView.activity.multiType.MultiTypeAdapter;
import com.idealcn.recyclerView.activity.multiType.MultiTypeData;

/**
 * @author: guoning
 * @date: 2018/12/11 15:35
 * @description:
 */
public class SingleLineAdapter implements IMultiTypeAdapter {
    @Override
    public boolean canParseItemType(MultiTypeData data) {
        return data.getType()==1;
    }

    @Override
    public MultiTypeAdapter.MultiTypeHolder onCreateViewHolder(ViewGroup parent) {

        return new MultiTypeAdapter.MultiTypeHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.adapter_single_line,parent,false));
    }

    @Override
    public void onBindViewHolder(MultiTypeAdapter.MultiTypeHolder holder, int position, MultiTypeData data) {
       TextView textView =  holder.itemView.findViewById(R.id.firstLine);
       textView.setText(data.getDesc());
    }
}
