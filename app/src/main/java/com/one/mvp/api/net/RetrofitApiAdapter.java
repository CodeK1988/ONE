package com.one.mvp.api.net;

import android.util.Log;

import com.google.gson.Gson;
import com.one.mvp.App;
import com.one.mvp.BuildConfig;
import com.one.mvp.api.config.ConstantApi;
import com.one.mvp.api.config.ServiceConfig;
import com.one.mvp.api.service.ApiService;
import com.one.mvp.util.HttpUtil;

import java.io.File;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.google.common.net.HttpHeaders.CACHE_CONTROL;
import static com.google.common.net.HttpHeaders.PRAGMA;
import static com.one.mvp.api.config.ConstantApi.APISERVICE_CONFIG;
import static com.one.mvp.api.config.ConstantApi.BaseYoungPath;
import static com.one.mvp.api.config.ConstantApi.baseUrl;
import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 *
 * Created by swplzj on 17/5/3.
 */

public class RetrofitApiAdapter {


  public static  <T> T  initService(ServiceConfig config) {

    String path = BaseYoungPath + "/cache/network";
    File fileP = new File(path);
    if (!fileP.exists()) {
      fileP.mkdirs();
    }

    Retrofit retrofit = new Retrofit.Builder()
            .client(new OkHttpClient().newBuilder().cache(new Cache(fileP, ConstantApi.maxStale))
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HEADERS:NONE))
                    .addInterceptor(chain -> {
                      Request request = chain.request();
                      if(!HttpUtil.isNetworkAvailable(App.getContext())){
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                        Log.d("RetrofitApiAdapter", "request: no network");
                      }else{
                        Log.d("RetrofitApiAdapter", "request: else");
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_NETWORK)
                                .build();
                      }

                      Response response = chain.proceed(request);
                      if(HttpUtil.isNetworkAvailable(App.getContext())){
                        Log.d("RetrofitApiAdapter", "response: isNetworkAvailable");
                        int maxAge = 0 * 60;
                        response.newBuilder()
                                .header(CACHE_CONTROL,"public,max-age"+maxAge)
                                .removeHeader(PRAGMA)
                                .build();
                      }else{
                        Log.d("RetrofitApiAdapter", "response: else");
                        int maxStale = 60 * 60 * 24;//网络断开 缓存一天
                        response.newBuilder()
                                .header(CACHE_CONTROL,"public,only-if-cached,max-stale="+maxStale)
                                .removeHeader(PRAGMA)
                                .build();
                      }

                      return response;
                    })
                    .addNetworkInterceptor(chain -> {// 处理 缓存丢失 504问题
                      Response response = chain.proceed(chain.request());
                      String header = response.header(CACHE_CONTROL);
                      if (header == null || header.contains("no-store") || header.contains("no-cache") ||
                              header.contains("must-revalidate") || header.contains("max-age=0")) {
                        return response.newBuilder()
                                .removeHeader(PRAGMA)
                                .header(CACHE_CONTROL, "public, max-age=" + 5000)
                                .build();
                      } else {
                        return response;
                      }
                    })
                    .retryOnConnectionFailure(true)
                    .build())
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
