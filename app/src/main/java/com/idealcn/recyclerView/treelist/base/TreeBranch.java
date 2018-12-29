package com.idealcn.recyclerView.treelist.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 分支,可以继续添加分支或者节点
 */
public class TreeBranch implements TreeNode {

    private List<TreeNode> treeNodeList = new ArrayList<>();

    /**
     * 当前选项是否展开
     */
    protected boolean expand;

    @Override
    public <T extends TreeNode> void add(T node) {
        treeNodeList.add(node);
    }

    public List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }
}
