package com.idealcn.recyclerView.activity.stagger.impl;

import android.graphics.Bitmap;

import com.idealcn.recyclerView.activity.stagger.ImageCache;

/**
 * @author: guoning
 * @date: 2018/12/11 18:00
 * @description:
 */
public class LocalImageCache implements ImageCache {
    @Override
    public Bitmap get(String path) {
        return null;
    }

    @Override
    public void put(String path, Bitmap bitmap) {

    }
}
