package com.idealcn.recyclerView.treelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.treelist.adapter.CityAdapter;
import com.idealcn.recyclerView.treelist.adapter.DistrictAdapter;
import com.idealcn.recyclerView.treelist.adapter.ProvinceAdapter;
import com.idealcn.recyclerView.treelist.adapter.RoadAdapter;
import com.idealcn.recyclerView.treelist.adapter.StreetAdapter;
import com.idealcn.recyclerView.treelist.adapter.TreeAdapter;

public class TreeListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list);

        Tree tree = new Tree("中国",1);

        Branch sh = new Branch("上海", 21);
        sh.add(new Leaf("浦东特区", 211));
        tree.add(sh);

        Branch zhejiang = new Branch("浙江",22);
        Branch hz = new Branch("杭州", 221);
        hz.add(new Leaf("西湖区", 2211));
        hz.add(new Leaf("滨江区", 2212));
        hz.add(new Leaf("上城区", 2213));

        Branch jianggan = new Branch("江干区",2214);


        Branch xiasha = new Branch("下沙", 22141);
        Leaf gaosha = new Leaf("高沙社区", 221411);
        gaosha.desc = "你在这里";
        xiasha.add(gaosha);
        xiasha.add(new Leaf("白杨街道",221412));

        jianggan.add(xiasha);
        jianggan.add(new Leaf("采荷街道",22142));
        hz.add(jianggan);


        zhejiang.add(hz);

        Branch taizhou = new Branch("台州",222);

        Branch jiaojiang = new Branch("椒江区",2221);
        jiaojiang.add(new Leaf("洪家街道",22211));
        taizhou.add(jiaojiang);
        zhejiang.add(taizhou);

        tree.add(zhejiang);

        Branch jiangsu = new Branch("江苏",23);
        Branch nj = new Branch("南京", 231);
        nj.add(new Leaf("玄武区", 2311));
        nj.add(new Leaf("雨花台区", 2312));
        nj.add(new Leaf("鼓楼区", 2312));
        jiangsu.add(nj);
        tree.add(jiangsu);

        Branch guangdong = new Branch("广东", 31);
        Branch guangzhou = new Branch("广州",311);
        guangzhou.add(new Leaf("白云区",3111));
        guangzhou.add(new Leaf("天河区",3112));
        guangdong.add(guangzhou);

        Branch shenzhen = new Branch("深圳", 312);
        shenzhen.add(new Leaf("宝安区",3121));
        shenzhen.add(new Leaf("福田区",3122));
        guangdong.add(shenzhen);
        tree.add(guangdong);




        tree.display();
        System.out.println("size: "+tree.size());

        RecyclerView treeList = findViewById(R.id.treeList);
        TreeAdapter treeAdapter = new TreeAdapter( tree.getTreeList());
        treeAdapter.addAdapter(new ProvinceAdapter());
        treeAdapter.addAdapter(new CityAdapter());
        treeAdapter.addAdapter(new DistrictAdapter());
        treeAdapter.addAdapter(new StreetAdapter());
        treeAdapter.addAdapter(new RoadAdapter());

        treeList.setLayoutManager(new LinearLayoutManager(this));
        treeList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        treeList.setAdapter(treeAdapter);



    }
}
