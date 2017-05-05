package com.one.mvp.base;

import android.widget.Toast;

import com.one.mvp.App;
import com.one.mvp.util.HttpUtil;

import rx.Subscriber;

/**
 * Created by swplzj on 17/5/3.
 */

public abstract class BaseSubscribe<T> extends Subscriber<T> {


  @Override
  public void onStart() {
    super.onStart();
    if(!HttpUtil.isNetworkAvailable(App.getContext())){
      Toast.makeText(App.getContext(), "当前网络不可用，请检查网络情况~", Toast.LENGTH_SHORT).show();
      onCompleted();
      return;
    }
  }

  @Override
  public void onCompleted() {
  }

  @Override
  public void onError(Throwable e) {
    onError(e);
  }

  @Override
  public void onNext(T t) {
    onSuccess(t);
  }


  public  abstract  void onSuccess(T result);

}
