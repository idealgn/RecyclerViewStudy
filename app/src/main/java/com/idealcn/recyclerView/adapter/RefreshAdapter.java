package com.idealcn.recyclerView.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * author: ideal-gn
 * date: 2017/10/6.
 */

public class RefreshAdapter extends BaseQuickAdapter<String,RefreshHolder> {
    public RefreshAdapter(){
        super(android.R.layout.simple_list_item_1);
    }
    @Override
    protected void convert(RefreshHolder helper, String item) {
        helper.setText(android.R.id.text1,item);
    }
}
