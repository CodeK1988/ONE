package com.one.mvp.api.service;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by swplzj on 17/5/3.
 */

public interface ApiService {


  /**
   * 首页接口
   */

  @GET
  Observable<String> getOneHome(@Url String url);

}
