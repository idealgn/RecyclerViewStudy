package com.idealcn.recyclerView.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idealcn.recyclerView.callback.ItemTouchHelperAdapter;
import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.callback.SimpleItemTouchHelperCallback;
import com.idealcn.recyclerView.databinding.ActivitySwipeBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ideal-gn on 2017/9/6.
 */

public class SwipeAndRemoveActivity extends AppCompatActivity{
    private List<String> dataList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySwipeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_swipe);

        for (int x = 0; x < 30; x++) {
            dataList.add("data----"+x);
        }

        SwipeAdapter swipeAdapter = new SwipeAdapter();
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT);
            }
        });
        binding.recyclerView.setAdapter(swipeAdapter);

        SimpleItemTouchHelperCallback callback = new SimpleItemTouchHelperCallback(swipeAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(binding.recyclerView);
    }

    private class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.SwipeHolder> implements ItemTouchHelperAdapter{


        @Override
        public SwipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View content = View.inflate(SwipeAndRemoveActivity.this, android.R.layout.simple_list_item_1, null);
            return new SwipeHolder(content);
        }

        @Override
        public void onBindViewHolder(SwipeHolder holder, int position) {
            holder.textView.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        @Override
        public void onItemMove(int from, int to) {
            if (from<to){
                for (int i = from; i < to; i++) {
                    Collections.swap(dataList,i,i+1);
                }
                notifyItemRangeChanged(from,to);
            }else {
                for (int i = to; i < from; i++) {
                    Collections.swap(dataList,i,i+1);
                }
                notifyItemRangeChanged(to,from);
            }

        }

        @Override
        public void onItemDismiss(int position) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }

        class SwipeHolder extends RecyclerView.ViewHolder{
            TextView textView;
            public SwipeHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
