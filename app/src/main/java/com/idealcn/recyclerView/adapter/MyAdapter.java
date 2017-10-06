package com.idealcn.recyclerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.callback.OnOpenItemClosedCallBack;
import com.idealcn.recyclerView.view.SlideDeleteLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ideal-gn on 2017/9/12.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>  implements OnOpenItemClosedCallBack {
    private List<String> dataList;
    private Context context;

    private List<SlideDeleteLayout> deleteLayoutList = new ArrayList<>();

    public MyAdapter(Context context,List<String> dataList){
        this.dataList =dataList;
        this.context = context;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_delete, null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.textView.setText(dataList.get(holder.getAdapterPosition()));
        SlideDeleteLayout deleteLayout = (SlideDeleteLayout) holder.itemView.findViewById(R.id.slideLayout);
        deleteLayout.setListener(new SlideDeleteLayout.OnSlideDeleteListener() {
            @Override
            public void onOpen(SlideDeleteLayout slideDelete) {
                closeAllOpenItemList();
                deleteLayoutList.add(slideDelete);
            }

            @Override
            public void onClose(SlideDeleteLayout slideDelete) {
                deleteLayoutList.remove(slideDelete);
            }

            @Override
            public void onClick() {
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
            }


        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private void closeAllOpenItemList() {
        for (SlideDeleteLayout deleteLayout : deleteLayoutList) {
            deleteLayout.isShowDelete(false);
        }
        deleteLayoutList.clear();
    }

    @Override
    public void close() {
        closeAllOpenItemList();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textView;
         MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.mTvContent);
        }
    }
}
