package com.idealcn.recyclerView.activity.stagger;

import android.graphics.Bitmap;

/**
 * @author: guoning
 * @date: 2018/12/11 17:15
 * @description:
 */
public interface ImageCache {
    Bitmap get(String path);
    void put(String path,Bitmap bitmap);
}
