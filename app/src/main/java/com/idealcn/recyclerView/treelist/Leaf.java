package com.idealcn.recyclerView.treelist;

import java.util.List;

public class Leaf extends Tree {


    public Leaf(String name, int id) {
        super(name, id);
    }

    @Override
    public void add(Tree tree) {
        throw new RuntimeException("级别不够,不能下设子公司或者办事处");
    }

    @Override
    public void remove(Tree tree) {
        throw new RuntimeException("级别不够,不能下设子公司或者办事处");
    }
}
