package com.one.mvp.api.net;

import com.google.gson.Gson;
import com.one.mvp.api.config.ServiceConfig;
import com.one.mvp.api.service.ApiService;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.one.mvp.api.config.ConstantApi.APISERVICE_CONFIG;
import static com.one.mvp.api.config.ConstantApi.BaseYoungPath;
import static com.one.mvp.api.config.ConstantApi.baseUrl;
import static com.one.mvp.api.config.ConstantApi.maxStale;

/**
 * Created by swplzj on 17/5/3.
 */

public class RetrofitApiAdapter {


  public static  <T> T  initService(ServiceConfig config) {

    String path = BaseYoungPath + "/cache/network";
    File fileP = new File(path);
    if (!fileP.exists()) {
      fileP.mkdirs();
    }

    Retrofit retrofit = new Retrofit.Builder().client(new OkHttpClient().newBuilder()
    .cache(new Cache(fileP,maxStale)).build())
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())//字符串
            .addConverterFactory(GsonConverterFactory.create(new Gson()))//gson
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//其他
            //.addCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor()))
            .build();

    int type = config.getDataSourceType();
    if(type==APISERVICE_CONFIG){
      return retrofit.create((Class<T>) ApiService.class);
    }
    return null;
  }


}
