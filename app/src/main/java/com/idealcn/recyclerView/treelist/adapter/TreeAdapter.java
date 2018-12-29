package com.idealcn.recyclerView.treelist.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.idealcn.recyclerView.treelist.base.IMultiTypeAdapter;
import com.idealcn.recyclerView.treelist.Branch;
import com.idealcn.recyclerView.treelist.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.TreeHolder> {

    private List<IMultiTypeAdapter<Tree,TreeHolder>> iMultiTypeAdapterList = new ArrayList<>();

    private List<Tree> treeList = new ArrayList<>();



    public TreeAdapter( @Nullable List<Tree> data) {
        this.treeList = data;
    }


    public void addAdapter(IMultiTypeAdapter<Tree,TreeHolder> adapter) {
        this.iMultiTypeAdapterList .add(adapter);
    }


    private List<Tree> removeList = new ArrayList<>();
    private void getExpandNumber( List<Tree> treeList) {
        for (Tree t : treeList) {
            removeList.add(t);
            if (t instanceof Branch){
                boolean expand = ((Branch) t).expand;
                if (expand){
                    ((Branch) t).expand = false;
                    List<Tree> list = t.getTreeList();
                    getExpandNumber( list);
                }
            }
        }
    }

    public List<Tree> getTreeList(){
        return treeList;
    }

    public void setTreeList(List<Tree> treeList) {
        this.treeList = treeList;
    }

    public void  addTree(Tree tree){
        this.treeList.add(tree);
    }

    public void addTreeList(List<Tree> list){
        treeList.addAll(list);
    }

    @NonNull
    @Override
    public TreeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IMultiTypeAdapter<Tree,TreeHolder> iMultiTypeAdapter = iMultiTypeAdapterList.get(viewType);
       final TreeHolder treeHolder = iMultiTypeAdapter.onCreateViewHolder(parent);


        treeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int layoutPosition = treeHolder.getLayoutPosition();
                Tree tree = getTreeList().get(layoutPosition);

                if (tree instanceof Branch){
                    Branch branch = (Branch) tree;
                    List<Tree> treeList = branch.getTreeList();

                    if (branch.expand){
                        try {
                            getExpandNumber(treeList);
                            getTreeList().removeAll(removeList);
                            notifyItemRangeRemoved(layoutPosition+1, removeList.size());
                            removeList.clear();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        getTreeList().addAll(layoutPosition+1, treeList);
                        notifyItemRangeChanged(layoutPosition+1,getTreeList().size() );
                    }
                    branch.expand =!branch.expand;
                }
            }
        });



        return treeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TreeHolder holder, int position) {
        IMultiTypeAdapter<Tree,TreeHolder> iMultiTypeAdapter = iMultiTypeAdapterList.get(getItemViewType(position));
        iMultiTypeAdapter.onBindViewHolder(holder,position,treeList.get(position));
    }


    @Override
    public int getItemCount() {
        return treeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        for (IMultiTypeAdapter<Tree,TreeHolder> iMultiTypeAdapter : iMultiTypeAdapterList) {
            if (iMultiTypeAdapter.canParseItemType(treeList.get(position))){
                return iMultiTypeAdapterList.indexOf(iMultiTypeAdapter);
            }
        }
       throw new RuntimeException("未找到对应的adapter");
    }

    public static class TreeHolder extends RecyclerView.ViewHolder{

        public TreeHolder(View itemView) {
            super(itemView);
        }
    }
}
