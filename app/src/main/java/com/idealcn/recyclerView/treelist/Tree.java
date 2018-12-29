package com.idealcn.recyclerView.treelist;

import com.idealcn.recyclerView.treelist.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Tree implements TreeNode {

    private List<Tree> treeList = new ArrayList<>();

    public void add(Tree tree){
        treeList.add(tree);
    }

    public void remove(Tree tree){
        treeList.remove(tree);
    }

    public String name;
    public int id;
    public String desc;


    public Tree(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public void  display(){
        for (Tree tree : treeList) {
            if (tree instanceof Branch){
                System.out.println("Branch: "+tree.name);
                tree.display();
                continue;
            }
                System.out.println("  leaf: "+tree.name);
        }
    }


    public int size(){
        return treeList.size();
    }

    public List<Tree> getTreeList(){
        return treeList;
    }

    @Override
    public void add(TreeNode node) {

    }
}
