package com.idealcn.recyclerView.treelist.base;

/**
 * TreeBranch和TreeLeaf都是节点,但是TreeBranch可以继续添加节点,而TreeLeaf不可以
 * 在这里定义add方法,是保证所有节点行为的一致性.
 */
public interface TreeNode {

   <T extends TreeNode>  void add(T node);

}
