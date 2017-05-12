package com.one.mvp;

import android.app.Application;
import android.content.Context;

import com.one.mvp.api.config.AppComponent;
import com.one.mvp.api.config.DaggerAppComponent;
import com.one.mvp.api.config.NetModule;
import com.one.mvp.api.config.ServiceConfig;

import static com.one.mvp.api.config.ConstantApi.APISERVICE_CONFIG;

/**
 * Created by swplzj on 17/5/2.
 */

public class App extends Application {

  private static AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    context = getApplicationContext();
    ServiceConfig config = ServiceConfig.builder().dataSourceType(APISERVICE_CONFIG)
            .build();
    appComponent = DaggerAppComponent.builder().netModule(new NetModule(config)).build();
  }

  public static AppComponent getAppComponent(){
    return appComponent;
  }

  /**
   * 获取到全局Context对象
   */
  private static Context context;

  public static Context getContext() {
    return context;
  }




}
