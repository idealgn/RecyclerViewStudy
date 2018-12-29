package com.idealcn.recyclerView.activity.stagger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.activity.stagger.bean.Beauty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: guoning
 * @date: 2018/12/11 16:21
 * @description:
 */
public class StaggerAdapter extends RecyclerView.Adapter<StaggerAdapter.StaggerHolder> {

    private List<Beauty> dataList = new ArrayList<>();
    private RecyclerView recyclerView;


    public void bindRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public StaggerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StaggerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_stagger,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaggerHolder holder, int position) {
        // TODO: 2018/12/12 只在item可见的时候才去加载显示图片
        System.out.println("onBindViewHolder: "+position);
        if (recyclerView==null){
            throw new RuntimeException("you should use method bindRecyclerView");
        }

        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
        if (null==layoutManager){
            throw new RuntimeException("you should set layoutmanger");
        }
//        layoutManager.findLastVisibleItemPositions()



        ImageLoader loader = ImageLoader.getLoader();
        loader.setUseLocalCache(false);
        String url = dataList.get(position).getUrl();
        holder.imageView.setTag(url);
        loader.display(holder.imageView, url);

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

    /**
     * 截取集合
     * @param start 起始位置
     * @param count 截取长度
     * @return 集合
     */
    public List<Beauty> getData(int start, int count) {

        if (start<0)
            throw new RuntimeException("起始位置不能小于0");

        if (count<=0)
            throw new RuntimeException("截取长度要大于0");

        int size = dataList.size();

        if (size <= start) {
           throw new RuntimeException("起始位置大于数据集合长度");
        }


        if (size>count && size - start >=count){
            return dataList.subList(start,count);
        }


        return dataList.subList(start,size - start);
    }

    public static class StaggerHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public StaggerHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.text);
        }
    }
}
