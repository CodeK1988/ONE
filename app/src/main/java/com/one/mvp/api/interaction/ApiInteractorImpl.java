package com.one.mvp.api.interaction;

import com.one.mvp.App;
import com.one.mvp.api.config.RxSchedulers;
import com.one.mvp.api.config.ServiceConfig;
import com.one.mvp.api.net.RetrofitApiAdapter;
import com.one.mvp.api.service.ApiService;
import com.one.mvp.base.BaseSubscribe;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.one.mvp.api.config.ConstantApi.APISERVICE_CONFIG;

/**
 * Created by swplzj on 17/5/3.
 */

public class ApiInteractorImpl implements ApiInteractor {

  public static ServiceConfig mConfig;
  private ApiService apiService;
  RxSchedulers rxSchedulers;

  @Inject
  public ApiInteractorImpl(ServiceConfig config) {
    mConfig = config;
    rxSchedulers = App.getAppComponent().rxSchedulers();
    switch (mConfig.getDataSourceType()){
      case APISERVICE_CONFIG:
        apiService = RetrofitApiAdapter.initService(config);
        break;
      default:
        break;
    }
  }

  /**
   * 首页接口
   * @param url
   * @param subscribe
   * @return
   */
  @Override
  public Subscription getOneHome(String url, BaseSubscribe<String> subscribe) {
    Observable<String> oneHome = apiService.getOneHome(url);
    Subscription subscription = oneHome
            .compose(applySchedulers())
            .subscribe(subscribe);
    return subscription;
  }





  /**
   * 常见到这种方式 不建议 这么做
   */
  <T> Observable<T> applySchedulers(Observable<T> observable) {
    return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }


  /**
   *   compose() is the only way to get the original Observable<T> from the stream.
   *   Therefore, operators that affect the whole stream (like subscribeOn() and observeOn())
   *   need to use compose()
   *
   *   这里不推荐使用 flatMap()
   *
   *   from http://blog.danlew.net/2015/03/02/dont-break-the-chain/
   */

  @SuppressWarnings("unchecked")
  <T> Observable.Transformer<T,T> applySchedulers(){
    return observable -> observable.subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.mainThread())
            .onErrorReturn(throwable -> null); //// TODO: 17/5/24
  }





}
