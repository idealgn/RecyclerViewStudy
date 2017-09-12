package com.idealcn.recyclerView.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.idealcn.recyclerView.adapter.MyAdapter;
import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.databinding.ActivityDeleteBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ideal-gn on 2017/9/12.
 */

public class DefineSwipeDeleteActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDeleteBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_delete);
        List<String> dataList = new ArrayList<>();
        for (int x = 0; x < 30; x++) {
            dataList.add("data----"+x);
        }
        final MyAdapter myAdapter = new MyAdapter(this,dataList);
        binding.recyclerView.setAdapter(myAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //上下滑动时,关闭打开的item
                if (recyclerView.getScrollState()==RecyclerView.SCROLL_STATE_DRAGGING&&Math.abs(dy)>0){
                    //这里采用了接口,保证了MyAdapter中定义的关闭方法不被外界所知道
                    myAdapter.close();
                }
            }
        });
    }
}
