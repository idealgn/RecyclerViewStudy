package com.idealcn.recyclerView.activity.multiType;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.idealcn.recyclerView.treelist.base.IMultiTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: guoning
 * @date: 2018/12/11 15:05
 * @description:
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.MultiTypeHolder> {

    private List<IMultiTypeAdapter<MultiTypeData,MultiTypeHolder>> iMultiTypeAdapterList = new ArrayList<>();
    private List<MultiTypeData> multiTypeDataList = new ArrayList<>();
    @NonNull
    @Override
    public MultiTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return iMultiTypeAdapterList.get(viewType).onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiTypeHolder holder, int position) {
        iMultiTypeAdapterList.get(getItemViewType(position)).onBindViewHolder(holder,position,multiTypeDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return multiTypeDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        MultiTypeData data = multiTypeDataList.get(position);
        for (IMultiTypeAdapter<MultiTypeData,MultiTypeHolder> iMultiTypeAdapter : iMultiTypeAdapterList) {
            if (iMultiTypeAdapter.canParseItemType(data)){
                return iMultiTypeAdapterList.indexOf(iMultiTypeAdapter);
            }
        }
        return super.getItemViewType(position);
    }

    public void addMultiTypeAdapter(IMultiTypeAdapter<MultiTypeData,MultiTypeHolder> adapter){
        iMultiTypeAdapterList.add(adapter);
    }

    public void addData(MultiTypeData data){
        multiTypeDataList.add(data);
    }

   public static class MultiTypeHolder extends RecyclerView.ViewHolder{

        public MultiTypeHolder(View itemView) {
            super(itemView);
        }
    }
}
