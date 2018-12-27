package com.idealcn.recyclerView.treelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.idealcn.recyclerView.R;

public class TreeListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list);

        Tree tree = new Tree("总部",1);

        Branch branch = new Branch("华东大区", 2);
        Leaf sh = new Leaf("上海分公司", 21);
        //sh.add(new Leaf("浦东特区", 211));
        branch.add(sh);
        Branch hz = new Branch("杭州分公司", 22);
        hz.add(new Leaf("西湖区办事处", 221));
        hz.add(new Leaf("滨江区办事处", 222));
        branch.add(hz);

        Branch nj = new Branch("南京分公司", 23);
        nj.add(new Leaf("玄武区办事处", 231));
        nj.add(new Leaf("雨花台区办事处", 232));
        branch.add(nj);

        Branch huanan = new Branch("华南大区", 3);

        Branch guangdong = new Branch("广东分公司", 31);
        guangdong.add(new Leaf("深圳特区办事处", 311));


        huanan.add(guangdong);


        tree.add(branch);
        tree.add(huanan);


        tree.display();
        System.out.println("size: "+tree.size());

        RecyclerView treeList = findViewById(R.id.treeList);
        TreeAdapter treeAdapter = new TreeAdapter(android.R.layout.simple_list_item_1, tree.getTreeList());
        treeList.setLayoutManager(new LinearLayoutManager(this));
        treeList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        treeList.setAdapter(treeAdapter);
    }
}
