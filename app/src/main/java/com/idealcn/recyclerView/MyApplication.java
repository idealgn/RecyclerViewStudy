package com.idealcn.recyclerView;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;

/**
 * Created by ideal-gn on 2017/9/12.
 */

public class MyApplication extends Application {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate() {
        super.onCreate();
        Configuration configuration = getResources().getConfiguration();
        int densityDpi = configuration.densityDpi;
        int uiMode = configuration.uiMode;
        float fontScale = configuration.fontScale;
        int hardKeyboardHidden = configuration.hardKeyboardHidden;
        int smallestScreenWidthDp = configuration.smallestScreenWidthDp;
        int navigation = configuration.navigation;
        int navigationHidden = configuration.navigationHidden;
        int orientation = configuration.orientation;
        int screenHeightDp = configuration.screenHeightDp;
        int screenLayout = configuration.screenLayout;
        int screenWidthDp = configuration.screenWidthDp;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int densityDpi1 = displayMetrics.densityDpi;
        float scaledDensity = displayMetrics.scaledDensity;

    }
}
