package com.idealcn.recyclerView.http;

import com.idealcn.recyclerView.stagger.BaseResponseBean;
import com.idealcn.recyclerView.stagger.Beauty;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author: guoning
 * @date: 2018/12/11 17:40
 * @description:
 */
public interface Api {

    // 福利
    @GET("http://gank.io/api/data/{type}/{pageCount}/{page}")
    Observable<BaseResponseBean<Beauty>> getBeautyList(@Path(value = "type", encoded = false)String type,
                                                       @Path("pageCount")int pageCount, @Path("page")int page);
}
