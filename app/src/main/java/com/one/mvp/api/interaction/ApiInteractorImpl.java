package com.one.mvp.api.interaction;

import com.one.mvp.api.config.ServiceConfig;
import com.one.mvp.api.net.RetrofitApiAdapter;
import com.one.mvp.api.service.ApiService;
import com.one.mvp.base.BaseResponse;
import com.one.mvp.base.BaseSubscribe;
import com.one.mvp.base.ResponseError;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.one.mvp.api.config.ConstantApi.APISERVICE_CONFIG;

/**
 * Created by swplzj on 17/5/3.
 */

public class ApiInteractorImpl implements ApiInteractor {

  public static ServiceConfig mConfig;
  private ApiService apiService;

  @Inject
  public ApiInteractorImpl(ServiceConfig config) {
    mConfig = config;
    switch (mConfig.getDataSourceType()){
      case APISERVICE_CONFIG:
        apiService = RetrofitApiAdapter.initService(config);
        break;
      default:
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


    Subscription subscription = oneHome.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn(throwable -> null)
           // .compose(resultHandle())
            .subscribe(subscribe);
    return subscription;
  }


  public static <T> Observable.Transformer<BaseResponse<T>, T> resultHandle(Observable<T> observable) {

    return new Observable.Transformer<BaseResponse<T>, T>() {
      @Override
      public Observable<T> call(Observable<BaseResponse<T>> responseObservable) {
        return responseObservable.flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
          @Override
          public Observable<T> call(BaseResponse<T> response) {
            if (!response.isSuccess()) {
              throw new ResponseError(response.e);
            }
            return returnSource(response.data);
          }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }


  /**
   * @param <T>
   * @return
   */
  public static <T> Observable.Transformer<BaseResponse<T>, T> resultHandle() {

    return new Observable.Transformer<BaseResponse<T>, T>() {
      @Override
      public Observable<T> call(Observable<BaseResponse<T>> responseObservable) {
        return responseObservable.flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
          @Override
          public Observable<T> call(BaseResponse<T> response) {
            if (!response.isSuccess()) {
              throw new ResponseError(response.e);
            }
            return returnSource(response.data);
          }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  /**
   * 分发 处理数据
   * @param data
   * @param <T>
   * @return
   */
  private static <T> Observable<T> returnSource(final T data) {

    return Observable.create(new Observable.OnSubscribe<T>() {
      @Override
      public void call(Subscriber<? super T> subscriber) {
        try {
          subscriber.onNext(data);
          subscriber.onCompleted();
        } catch (Exception e) {
          subscriber.onError(e);
        }

      }
    });
  }


}
