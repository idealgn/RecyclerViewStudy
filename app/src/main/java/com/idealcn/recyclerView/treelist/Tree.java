package com.idealcn.recyclerView.treelist;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private List<Tree> treeList = new ArrayList<>();

    public void add(Tree tree){
        treeList.add(tree);
    }

    public void remove(Tree tree){
        treeList.remove(tree);
    }

    public String name;
    public int id;
    public boolean expand;

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
}
