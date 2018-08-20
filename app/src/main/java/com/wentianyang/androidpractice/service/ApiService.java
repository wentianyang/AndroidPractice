package com.wentianyang.androidpractice.service;

import com.wentianyang.androidpractice.model.GankItem;
import com.wentianyang.base.model.BaseModel;
import io.reactivex.Flowable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Date 创建时间:  2018/8/10
 * @Author: YTW
 * @Description:
 **/

public interface ApiService {

    @GET("data/{type}/{number}/{page}")
    Flowable<BaseModel<List<GankItem>>> getGankData(@Path("type") String type,
        @Path("number") int pageSize,
        @Path("page") int pageNum);

//    @GET("#/discover/toplist")
//    Flowable<BaseModel<List<GankItem>>> getGankData();
}
