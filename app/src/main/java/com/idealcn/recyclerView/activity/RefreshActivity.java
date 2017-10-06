package com.idealcn.recyclerView.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ajguan.library.EasyRefreshLayout;
import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.adapter.RefreshAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ideal-gn
 * date: 2017/10/6.
 *  刷新和加载更多
 */

public class RefreshActivity extends AppCompatActivity {

    EasyRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    RefreshAdapter refreshAdapter;
    int page;
    List<String> dataList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        refreshLayout = (EasyRefreshLayout) findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        refreshAdapter = new RefreshAdapter();
        refreshAdapter.setNewData(dataList);
        recyclerView.setAdapter(refreshAdapter);

        refreshLayout.autoRefresh();
        refreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                page++;
                getDataList();
            }

            @Override
            public void onRefreshing() {
                dataList.clear();
                page = 1;
                getDataList();
            }
        });
    }

    private void getDataList(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isLoading()) {
                    List<String> temp = new ArrayList<>();
                    for (int x = 0; x < 10; x++) {
                        temp.add("加载数据---"+(dataList.size()+x));
                    }
                    refreshAdapter.addData(dataList.size(),temp);
                    refreshLayout.loadMoreComplete();
                }else {
                    List<String> temp = new ArrayList<>();
                    for (int x = 0; x < 10; x++) {
                        temp.add("刷新数据---"+x);
                    }
                    refreshAdapter.addData(0,temp);
                    refreshLayout.refreshComplete();
                }
            }
        },2000);
    }
}
