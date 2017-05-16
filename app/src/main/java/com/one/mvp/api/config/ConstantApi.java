package com.one.mvp.api.config;

import android.os.Environment;

/**
 * Created by swplzj on 17/5/3.
 */

public interface ConstantApi {




  String BaseYoungPath = Environment.getExternalStorageDirectory().getAbsolutePath() + ConstantApi.CachePath;
  String CachePath = "/com.one.mvp";
  int maxStale = 10 * 1024 * 1024;//缓存100M
  String responseCode = "9999";//请求成功
  int APISERVICE_CONFIG = 1;

  String baseUrl = "http://v3.wufazhuce.com:8000/api/";
  String OneFragmentApi = baseUrl+"hp/more/0?version=v3.5.3";//首页接口

}
