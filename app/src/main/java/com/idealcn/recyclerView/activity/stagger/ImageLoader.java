package com.idealcn.recyclerView.activity.stagger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.idealcn.recyclerView.MyApplication;
import com.idealcn.recyclerView.activity.stagger.cache.ImageCache;
import com.idealcn.recyclerView.activity.stagger.cache.ImageMemoryCache;
import com.idealcn.recyclerView.activity.stagger.cache.LocalImageCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: guoning
 * @date: 2018/12/11 17:14
 * @description:
 */
public class ImageLoader {

    private ImageCache memoryCache;

    private ImageCache localImageCache;

    private boolean useLocalCache;

    private ImageLoader() {
        memoryCache = new ImageMemoryCache();
    }

    private OkHttpClient okHttpClient;

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .cache(new Cache(MyApplication.getHttpCachePath(),10*1024*1024))
                    .build();
        }
        return okHttpClient;
    }


    private static ImageLoader loader;

    public static ImageLoader getLoader() {

        if (null == loader) {
            synchronized (ImageLoader.class) {
                if (null == loader) {
                    loader = new ImageLoader();
                }
            }
        }
        return loader;
    }

    public void setUseLocalCache(boolean useLocalCache) {
        this.useLocalCache = useLocalCache;
        if (localImageCache == null) {
            localImageCache = new LocalImageCache();
        }
    }

    /**
     * 判断ImageView和图片url是否对应
     *
     * @param imageView imageview
     * @param path      图片url
     * @return boolean
     */
    private boolean checkImageViewAndPath(ImageView imageView, String path) {
        String tag = (String) imageView.getTag();
        boolean equals = path.equals(tag);
        System.out.println("checkImageViewAndPath: " + equals);
        return !equals;
    }

    public void display(final ImageView imageView, final String path) {


        Bitmap bitmap = memoryCache.get(path);
        if (bitmap != null) {
            if (checkImageViewAndPath(imageView, path)) return;
            imageView.setImageBitmap(bitmap);
            return;
        }

        if (useLocalCache) {
            bitmap = localImageCache.get(path);
            if (null != bitmap) {
                if (checkImageViewAndPath(imageView, path)) return;
                imageView.setImageBitmap(bitmap);
                localImageCache.put(path, bitmap);
                return;
            }
        }


        OkHttpClient client = getOkHttpClient();


        final Request request = new Request.Builder()
                .url(path)
                .build();
        Call call = client
                .newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.code() == 200) {
                    InputStream inputStream = response.body().byteStream();
                    final Bitmap temp = BitmapFactory.decodeStream(inputStream);
                    imageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (checkImageViewAndPath(imageView, path)) return;
                            imageView.setImageBitmap(temp);
                            memoryCache.put(path, temp);
                            if (useLocalCache) localImageCache.put(path, temp);
                        }
                    }, 100);
                }
            }
        });


    }


}
