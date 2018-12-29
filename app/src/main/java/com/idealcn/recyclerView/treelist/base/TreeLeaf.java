package com.idealcn.recyclerView.treelist.base;

/**
 * 节点,不能添加
 */
public class TreeLeaf implements TreeNode {
    @Override
    public void add(TreeNode node) {
        throw new RuntimeException("没有添加子节点的权限");
    }
}
