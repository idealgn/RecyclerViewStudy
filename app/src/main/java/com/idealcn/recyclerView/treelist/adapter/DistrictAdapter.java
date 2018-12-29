package com.idealcn.recyclerView.treelist.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.treelist.base.IMultiTypeAdapter;
import com.idealcn.recyclerView.treelist.Tree;

public class DistrictAdapter implements IMultiTypeAdapter<Tree,TreeAdapter.TreeHolder> {
    @Override
    public boolean canParseItemType(Tree data) {

        return String.valueOf(data.id).length()==4;
    }

    @Override
    public TreeAdapter.TreeHolder onCreateViewHolder(ViewGroup parent) {

        return new TreeAdapter.TreeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_district,parent,false));
    }

    @Override
    public  void onBindViewHolder(TreeAdapter.TreeHolder holder, int position, Tree data) {
      TextView tvDistrictName =  holder.itemView.findViewById(R.id.adapter_district_name);
        tvDistrictName.setText(data.name);

//        TextView tvProvinceDesc =  holder.itemView.findViewById(R.id.adapter_province_desc);
//        tvProvinceDesc.setText(data.desc);
    }
}
