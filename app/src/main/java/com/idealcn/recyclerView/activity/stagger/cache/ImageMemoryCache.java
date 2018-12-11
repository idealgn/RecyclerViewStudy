package com.idealcn.recyclerView.activity.stagger.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: guoning
 * @date: 2018/12/11 17:20
 * @description: 内存缓存
 */
public class ImageMemoryCache implements ImageCache {
    private int freeMemory = (int) (Runtime.getRuntime().maxMemory()/4);
    private LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(freeMemory){
        @Override
        protected int sizeOf(String key, Bitmap value) {

            return value.getByteCount();
        }
    };
    @Override
    public Bitmap get(String path) {

        return lruCache.get(path);
    }

    @Override
    public void put(String path, Bitmap bitmap) {
        lruCache.put(path,bitmap);
    }
}
