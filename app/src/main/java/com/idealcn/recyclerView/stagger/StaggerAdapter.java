package com.idealcn.recyclerView.stagger;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.idealcn.recyclerView.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: guoning
 * @date: 2018/12/11 16:21
 * @description:
 */
public class StaggerAdapter extends RecyclerView.Adapter<StaggerAdapter.StaggerHolder> {

    private List<Beauty> dataList = new ArrayList<>();

    private Context context;
    public StaggerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public StaggerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StaggerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_stagger,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaggerHolder holder, int position) {
//        AssetManager assetManager = context.getAssets();
//        try {
//            InputStream inputStream = assetManager.open("imgs/"+dataList.get(position));
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            holder.textView.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ImageLoader.getLoader(context).display(holder.textView,dataList.get(position).getUrl());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<Beauty> data){
        int start = dataList.size();
        dataList.addAll(data);
        notifyItemRangeInserted(start,data.size());
    }

    public static class StaggerHolder extends RecyclerView.ViewHolder{
        public ImageView textView;
        public StaggerHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}