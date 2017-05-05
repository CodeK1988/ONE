package com.one.mvp.api.interaction;

import com.one.mvp.base.BaseSubscribe;

import rx.Subscription;

/**
 * Created by swplzj on 17/5/3.
 */

public interface ApiInteractor {


  /**
   * 首页接口
   * @param url
   * @param subscribe
   * @return
   */
  Subscription getOneHome(String url, BaseSubscribe<String> subscribe);

}
