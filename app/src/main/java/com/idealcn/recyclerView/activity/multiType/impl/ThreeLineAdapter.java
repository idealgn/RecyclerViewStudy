package com.idealcn.recyclerView.activity.multiType.impl;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.treelist.base.IMultiTypeAdapter;
import com.idealcn.recyclerView.activity.multiType.MultiTypeAdapter;
import com.idealcn.recyclerView.activity.multiType.MultiTypeData;

/**
 * @author: guoning
 * @date: 2018/12/11 15:41
 * @description:
 */
public class ThreeLineAdapter implements IMultiTypeAdapter<MultiTypeData,MultiTypeAdapter.MultiTypeHolder> {

    @Override
    public boolean canParseItemType(MultiTypeData data) {
        return data.getType()==3;
    }

    @Override
    public MultiTypeAdapter.MultiTypeHolder onCreateViewHolder(ViewGroup parent) {
        return new MultiTypeAdapter.MultiTypeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_three_line,parent,false));
    }

    @Override
    public void onBindViewHolder(MultiTypeAdapter.MultiTypeHolder holder, int position, MultiTypeData data) {
      TextView first =  holder.itemView.findViewById(R.id.firstLine);
        TextView second =  holder.itemView.findViewById(R.id.secondLine);
        TextView third =  holder.itemView.findViewById(R.id.thirdLine);


        first.setText(data.getDesc());
        second.setText(data.getDesc());
        third.setText(data.getDesc());

    }
}
