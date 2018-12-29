package com.idealcn.recyclerView.activity.stagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ajguan.library.EasyRefreshLayout;
import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.activity.stagger.bean.BaseResponseBean;
import com.idealcn.recyclerView.activity.stagger.bean.Beauty;
import com.idealcn.recyclerView.http.RetrofitClient;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: guoning
 * @date: 2018/12/11 16:18
 * @description:
 *  todo 瀑布流顶部会出现一片空白
 */
public class StaggeredActivity extends AppCompatActivity  {

    private         StaggerAdapter staggerAdapter;
    private int page = 0;
    private boolean isRefreshing = true;
    private boolean isFirstRefreshing = true;
    private         EasyRefreshLayout refreshLayout;

    private StaggerDiffCallback diffCallback = new StaggerDiffCallback();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger);



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refreshLayout);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL){
            @Override
            public boolean isAutoMeasureEnabled() {
                return true;
            }
        };
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        staggerAdapter = new StaggerAdapter();
        staggerAdapter.bindRecyclerView(recyclerView);
        recyclerView.setAdapter(staggerAdapter);

        recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                int adapterPosition = holder.getAdapterPosition();
                System.out.println("adapterPosition: "+adapterPosition+(holder.isRecyclable()?"被":"没有被")+"回收");
            }
        });


        refreshLayout.autoRefresh();
        refreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                ++page;
                isRefreshing = false;
                requestImageList();
            }

            @Override
            public void onRefreshing() {
                page = 0;
                isRefreshing = true;
                requestImageList();
            }
        });
    }

    private void requestImageList(){
        //@GET("http://gank.io/api/data/{type}/{pageCount}/{page}")
        RetrofitClient.newInstance().getApi().getBeautyList("福利",20,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isRefreshing){
                            refreshLayout.refreshComplete();
                        }else {
                            refreshLayout.loadMoreComplete();
                        }
                    }
                })

                .subscribe(new Observer<BaseResponseBean<Beauty>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponseBean<Beauty> beautyBaseResponseBean) {
                        List<Beauty> beautyList = beautyBaseResponseBean.getResults();

                        if (isRefreshing && !isFirstRefreshing){
                            //刷新的情况下,比对前面二十条数据
                            diffCallback.setNewBeautyList(beautyList);
                            diffCallback.setOldBeautyList(staggerAdapter.getData(0,20));
                            DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(staggerAdapter);
                        }else {
                            isFirstRefreshing = false;
                            staggerAdapter.addData(beautyList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private class StaggerDiffCallback extends DiffUtil.Callback{

        private List<Beauty> newBeautyList;
        private List<Beauty> oldBeautyList;

        public void setNewBeautyList(List<Beauty> newBeautyList) {
            this.newBeautyList = newBeautyList;
        }

        public void setOldBeautyList(List<Beauty> oldBeautyList) {
            this.oldBeautyList = oldBeautyList;
        }

        @Override
        public int getOldListSize() {
            return oldBeautyList.size();
        }

        @Override
        public int getNewListSize() {
            return newBeautyList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            Beauty newBeauty = newBeautyList.get(newItemPosition);
            Beauty oldBeauty = oldBeautyList.get(oldItemPosition);
            if (null!=newBeauty && null!=oldBeauty){
                return newBeauty.get_id() .equals(oldBeauty.get_id()) ;
            }
            return false;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Beauty newBeauty = newBeautyList.get(newItemPosition);
            Beauty oldBeauty = oldBeautyList.get(oldItemPosition);
            if (null!=newBeauty && null!=oldBeauty){
                return newBeauty.getUrl() .equals(oldBeauty.getUrl()) ;
            }
            return false;
        }
    }




    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        System.out.println("onTrimMemory： "+level);
    }
}
