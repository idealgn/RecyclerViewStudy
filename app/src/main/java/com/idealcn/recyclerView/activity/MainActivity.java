package com.idealcn.recyclerView.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.idealcn.recyclerView.decoration.LeftItemDecoration;
import com.idealcn.recyclerView.adapter.MyAdapter;
import com.idealcn.recyclerView.decoration.MyItemDecoration;
import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.listener.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "handler";


    private   MyAdapter myAdapter;

    private String[] initArr = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N",
    "O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private List<String> dataList = new ArrayList<>();
    private Map<Integer,String> floatingTiltesMap = new HashMap<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            for (int x = 0; x < Math.random()*26; x++) {
                String start = initArr[x];
                for (int y = 0; y < Math.random()*10; y++) {
                    dataList.add(start+"---"+y);
                }
            }
        for (int x = 0; x < dataList.size(); x++) {
            String text = dataList.get(x).substring(0, 1);

            if (floatingTiltesMap.containsValue(text))continue;
            floatingTiltesMap.put(x, text);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT);
            }
        });
        recyclerView.addItemDecoration(new LeftItemDecoration(this,floatingTiltesMap));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new MyItemDecoration(this,floatingTiltesMap));

        myAdapter = new MyAdapter(this,dataList);

        recyclerView.setAdapter(myAdapter);

//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (recyclerView.getScrollState()==RecyclerView.SCROLL_STATE_DRAGGING&&Math.abs(dy)>0){
//                    myAdapter.close();
//                }
//            }
//        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState()==RecyclerView.SCROLL_STATE_DRAGGING&&Math.abs(dy)>0){
                    myAdapter.close();
                }
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(recyclerView){

            @Override
            protected void onItemLongClick(RecyclerView.ViewHolder childViewHolder) {
                int layoutPosition = childViewHolder.getLayoutPosition();
                System.out.println("onItemLongClick:  "+layoutPosition);
            }

            @Override
            protected void onItemClick(RecyclerView.ViewHolder childViewHolder) {
                int layoutPosition = childViewHolder.getLayoutPosition();
                System.out.println("onItemClick:  "+layoutPosition);
            }
        });

    }

    public void update(View view) {
            String start = initArr[(int) (Math.random()*26)];
            for (int y = 0; y < Math.random()*10; y++) {
                dataList.add(y,start+"---"+y);
            }
        for (int x = 0; x < dataList.size(); x++) {
            floatingTiltesMap.put(x,dataList.get(x).substring(0,1));
        }
            myAdapter.notifyDataSetChanged();
    }


}
