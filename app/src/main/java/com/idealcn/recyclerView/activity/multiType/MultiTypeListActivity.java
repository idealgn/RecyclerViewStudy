package com.idealcn.recyclerView.activity.multiType;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.activity.multiType.impl.SingleLineAdapter;
import com.idealcn.recyclerView.activity.multiType.impl.ThreeLineAdapter;
import com.idealcn.recyclerView.activity.multiType.impl.TwoLineAdapter;

/**
 * @author: guoning
 * @date: 2018/12/11 15:02
 * @description:
 */
public class MultiTypeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);
       RecyclerView recyclerView =  findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
        MultiTypeData multiTypeData;
        for (int i = 1; i <= 30; i++) {
            multiTypeData = new MultiTypeData();
            int index = i % 3;
            index = index==0?3:index;
            multiTypeData.setDesc("第"+index+"种布局");
            multiTypeData.setType(index);
            multiTypeAdapter.addData(multiTypeData);
        }
        multiTypeAdapter.addMultiTypeAdapter(new SingleLineAdapter());
        multiTypeAdapter.addMultiTypeAdapter(new TwoLineAdapter());
        multiTypeAdapter.addMultiTypeAdapter(new ThreeLineAdapter());

        recyclerView.setAdapter(multiTypeAdapter);
    }
}
