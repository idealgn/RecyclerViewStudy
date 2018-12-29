package com.idealcn.recyclerView.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.idealcn.recyclerView.R;
import com.idealcn.recyclerView.databinding.ActivitySplashBinding;
import com.idealcn.recyclerView.activity.multiType.MultiTypeListActivity;
import com.idealcn.recyclerView.activity.stagger.StaggeredActivity;
import com.idealcn.recyclerView.treelist.TreeListActivity;

/**
 * Created by ideal-gn on 2017/9/6.
 */

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
      ActivitySplashBinding binding =  DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setClick(this);
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//           int result = getResources().getDimensionPixelSize(resourceId);
//            System.out.println(result);
//        }
//
//        int navigation = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
//        if (navigation>0){
//            int dimensionPixelSize = getResources().getDimensionPixelSize(navigation);
//            System.out.println(dimensionPixelSize);
//        }
//
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        int heightPixels = displayMetrics.heightPixels;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contact_list:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.itemTouchHelper:
                startActivity(new Intent(this,SwipeAndRemoveActivity.class));
                break;
            case R.id.viewDrawHelper:
                startActivity(new Intent(this,DefineSwipeDeleteActivity.class));
                break;
            case R.id.refresh:
                startActivity(new Intent(this,RefreshActivity.class));
                break;
            case R.id.multiType:
                startActivity(new Intent(this,MultiTypeListActivity.class));
                break;
            case R.id.pubuliu:
                startActivity(new Intent(this,StaggeredActivity.class));
                break;
            case R.id.tree:
                startActivity(new Intent(this,TreeListActivity.class));
                break;
                default:break;
        }
    }
}
