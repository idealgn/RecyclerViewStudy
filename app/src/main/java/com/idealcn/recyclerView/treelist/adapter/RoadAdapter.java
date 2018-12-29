package com.idealcn.recyclerView.treelist.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.treelist.base.IMultiTypeAdapter;
import com.idealcn.recyclerView.treelist.Tree;

public class RoadAdapter implements IMultiTypeAdapter<Tree,TreeAdapter.TreeHolder> {
    @Override
    public boolean canParseItemType(Tree data) {
        return String.valueOf(data.id).length()==6;
    }

    @Override
    public TreeAdapter.TreeHolder onCreateViewHolder(ViewGroup parent) {
        return new TreeAdapter.TreeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_road,parent,false));
    }

    @Override
    public void onBindViewHolder(TreeAdapter.TreeHolder holder, int position, Tree data) {
       TextView tvRoadName =  holder.itemView.findViewById(R.id.adapter_road_name);
        tvRoadName.setText(data.name);

        TextView tvDescName =  holder.itemView.findViewById(R.id.adapter_road_desc);
        tvDescName.setText(data.desc);
    }
}
