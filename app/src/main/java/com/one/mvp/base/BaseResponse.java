package com.one.mvp.base;

import com.one.mvp.api.config.ConstantApi;


/**
 * Created by swplzj on 17/5/3.
 */

public class BaseResponse<T> {

  public String e;//请求状态码
  public T data;//返回数据
  public Boolean isSuccess(){
    return e.equals(ConstantApi.responseCode);
  }


}
