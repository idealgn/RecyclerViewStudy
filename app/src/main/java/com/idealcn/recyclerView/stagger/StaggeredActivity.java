package com.idealcn.recyclerView.stagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.http.RetrofitClient;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: guoning
 * @date: 2018/12/11 16:18
 * @description:
 */
public class StaggeredActivity extends AppCompatActivity {

    private         StaggerAdapter staggerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);

        staggerAdapter = new StaggerAdapter(this);
//        for (int i = 0; i < 50; i++) {
//            staggerAdapter.addData(String.valueOf(i));
//        }
//        try {
//            String[] list = getAssets().list("imgs");
//            for (String s : list) {
//                staggerAdapter.addData(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        recyclerView.setAdapter(staggerAdapter);

        requestImageList(0);
    }

    private void requestImageList(int page){
        //@GET("http://gank.io/api/data/{type}/{pageCount}/{page}")
        RetrofitClient.newInstance().getApi().getBeautyList("福利",20,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponseBean<Beauty>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponseBean<Beauty> beautyBaseResponseBean) {
                        List<Beauty> beautyList = beautyBaseResponseBean.getResults();
                        staggerAdapter.addData(beautyList);
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
}
