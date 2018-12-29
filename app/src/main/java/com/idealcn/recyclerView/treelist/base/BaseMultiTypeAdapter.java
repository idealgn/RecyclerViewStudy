package com.idealcn.recyclerView.treelist.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BaseMultiTypeAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<IMultiTypeAdapter<TreeNode,VH>> iMultiTypeAdapterList = new ArrayList<>();
    private List<TreeNode> dataList = new ArrayList<>();
    //合并时,需要动态去除的数据
    private List<TreeNode> removeList = new ArrayList<>();
    private int removeCount = 0;


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final VH holder = iMultiTypeAdapterList.get(viewType).onCreateViewHolder(parent);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int layoutPosition = holder.getLayoutPosition();
                if (layoutPosition == RecyclerView.NO_POSITION)return;
                TreeNode t = getDataList().get(layoutPosition);
                if (t instanceof TreeBranch){
                    List<TreeNode> branchList = ((TreeBranch) t).getTreeNodeList();
                    if (((TreeBranch) t).expand){
                        removeList(branchList);
                        notifyItemRangeChanged(layoutPosition+1,removeCount);
                    }else {
                        addAll(branchList);
                    }
                }
            }
        });
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        TreeNode data = dataList.get(holder.getLayoutPosition());
        IMultiTypeAdapter<TreeNode, VH> iMultiTypeAdapter = iMultiTypeAdapterList.get(getItemViewType(position));
        iMultiTypeAdapter.onBindViewHolder(holder,position,data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        for (IMultiTypeAdapter<TreeNode, VH> iMultiTypeAdapter : iMultiTypeAdapterList) {
            if (iMultiTypeAdapter.canParseItemType(dataList.get(position))){
                return iMultiTypeAdapterList.indexOf(iMultiTypeAdapter);
            }
        }
        throw new RuntimeException("未设置对应的adapter");
    }


    public void add(TreeNode t){
        dataList.add(t);
        notifyItemInserted(dataList.size()-1);
    }

    public void addAll(List<TreeNode> list){
        addAll(dataList.size(),list);
    }

    public void addAll(int index,List<TreeNode> list){
        int size = dataList.size();
        dataList.addAll(index,list);
        notifyItemRangeChanged(size+1,list.size());
    }


    public List<TreeNode> getDataList(){
        return dataList;
    }

    public void addMultiTypeAdapter(IMultiTypeAdapter<TreeNode,VH> iMultiTypeAdapter){
        iMultiTypeAdapterList.add(iMultiTypeAdapter);
    }

    /**
     * 收拢时,移除子节点数据
     * @param branchList
     */
    private  void removeList(List<TreeNode> branchList) {
        for (TreeNode treeNode : branchList) {
            ++removeCount;
            dataList.remove(treeNode);
            if (treeNode instanceof TreeBranch){
                List<TreeNode> treeNodeList = ((TreeBranch) treeNode).getTreeNodeList();
                removeList(treeNodeList);
            }
        }
    }

    private void getRemoveList(List<TreeNode> list){

    }
}
