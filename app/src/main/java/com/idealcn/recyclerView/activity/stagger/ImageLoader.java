package com.idealcn.recyclerView.activity.stagger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.idealcn.recyclerView.activity.stagger.impl.ImageMemoryCache;
import com.idealcn.recyclerView.activity.stagger.impl.LocalImageCache;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

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
    private WeakReference<Context> weakReference;

    private ImageCache localImageCache;

    private boolean useLocalCache;

    private ImageLoader(Context context){
        weakReference = new WeakReference<>(context);
        memoryCache = new ImageMemoryCache();
    }

    private static ImageLoader loader;
    public static ImageLoader getLoader(Context context){

            if (null==loader){
                synchronized (ImageLoader.class){
                    if (null==loader) {
                        loader = new ImageLoader(context);
                    }
                }
            }
            return loader;
    }

    public void setUseLocalCache(boolean useLocalCache){
        this.useLocalCache = useLocalCache;
        if (localImageCache==null){
            localImageCache = new LocalImageCache();
        }
    }


    public void display(final ImageView imageView, final String path){
       Bitmap bitmap =  memoryCache.get(path);
       if (bitmap!=null){
           imageView.setImageBitmap(bitmap);
           return;
       }

       if (useLocalCache){
         bitmap =   localImageCache.get(path);
         if (null!=bitmap){
             imageView.setImageBitmap(bitmap);
             localImageCache.put(path,bitmap);
             return;
         }
       }


        OkHttpClient client = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder()
                .url(path)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.code() == 200){
                    InputStream inputStream = response.body().byteStream();
                    Bitmap temp = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(temp);
                    memoryCache.put(path,temp);
                    localImageCache.put(path,temp);
                }
            }
        });



    }
}
