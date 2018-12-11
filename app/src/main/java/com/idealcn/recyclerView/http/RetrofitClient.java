package com.idealcn.recyclerView.http;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final RetrofitClient INSTANCE = new RetrofitClient();


    public static RetrofitClient newInstance(){
        return INSTANCE;
    }

    private Retrofit retrofit;
    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request();
                                Response response = chain.proceed(request);
//                                boolean successful = response.isSuccessful();
//                                String message = response.message();
//                                ResponseBody body = response.body();
                                //body.string()多次调用会触发异常 java.lang.IllegalStateException: closed
//                                String string = body.string();
                                return response;
                            }
                        })
                        .build())
                .baseUrl("http://www.wanandroid.com/")
                .build();
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
