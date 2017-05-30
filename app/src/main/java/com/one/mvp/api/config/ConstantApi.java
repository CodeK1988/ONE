package com.one.mvp.api.config;

import android.os.Environment;

/**
 *  周五不想敲代码 ~~~~(>_<)~~~~
 * Created by swplzj on 17/5/3.
 */

public interface ConstantApi {


  String BaseYoungPath = Environment.getExternalStorageDirectory().getAbsolutePath() + ConstantApi.CachePath;// 缓存根目录
  String CachePath = "/com.one.mvp";
  int maxStale = 10 * 1024 * 1024;//缓存10M
  String responseCode = "9999";//请求成功
  int APISERVICE_CONFIG = 1; // 临时参数 决定Service
  String baseUrl = "http://v3.wufazhuce.com:8000/api/";// baseUrl 全局可替换
  String OneFragmentApi = baseUrl+"hp/more/0?version=v3.5.3";//首页接口
  String appIdBugly = "bd35c4ac62";//腾讯集成



}
