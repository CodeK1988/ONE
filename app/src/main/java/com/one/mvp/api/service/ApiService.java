package com.one.mvp.api.service;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 *
 * api 接口类
 * Created by swplzj on 17/5/3.
 */

public interface ApiService {


  /**
   * 首页接口
   */

  @GET
  Observable<String> getOneHome(@Url String url);


  /**
   * 以下未验证 不推荐
   * @param url
   * @param map
   * @param <T>
   * @return
   */

  @GET
  <T> Observable<T> get(@Url String url, @QueryMap HashMap<String,T> map);

  @FormUrlEncoded
  @POST
  <T> Observable<T> post(@Url String url, @FieldMap HashMap<String,T> map);

  /**
   * 上传文件及参数
   */
  @Multipart
  @POST
  <T> Observable<T> uploadFile(@Url String url, @Part List<MultipartBody.Part> files, @PartMap HashMap<String, RequestBody> params);

}




















