package com.idealcn.recyclerView.stagger.impl;

import android.graphics.Bitmap;

import com.idealcn.recyclerView.stagger.ImageCache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: guoning
 * @date: 2018/12/11 17:20
 * @description:
 */
public class ImageMemoryCache implements ImageCache {
    private Map<String,Bitmap> bitmapMap = new HashMap<>();
    @Override
    public Bitmap get(String path) {

        return bitmapMap.get(path);
    }

    @Override
    public void put(String path, Bitmap bitmap) {
        bitmapMap.put(path,bitmap);
    }
}
