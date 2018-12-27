package com.idealcn.recyclerView.treelist;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class TreeAdapter extends BaseQuickAdapter<Tree,BaseViewHolder> {
    public TreeAdapter(int layoutResId, @Nullable List<Tree> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Tree item) {
        helper.setText(android.R.id.text1, item.name);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        final BaseViewHolder holder = super.onCreateDefViewHolder(parent, viewType);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int layoutPosition = holder.getLayoutPosition();
                Tree tree = getData().get(layoutPosition);



                if (tree instanceof Branch){
                    Branch branch = (Branch) tree;
                    List<Tree> treeList = branch.getTreeList();

                    if (tree.expand){
                        getData().removeAll(treeList);
                        notifyItemRangeRemoved(layoutPosition+1, treeList.size());
                    }else {
                        getData().addAll(layoutPosition+1, treeList);
                        notifyItemRangeChanged(layoutPosition+1,treeList.size() );
                    }
                    tree.expand =!tree.expand;
                }
            }
        });
        return holder;
    }
}
