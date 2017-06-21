package com.one.mvp.base;

import android.arch.lifecycle.LifecycleActivity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.InflateException;

import com.one.mvp.App;
import com.one.mvp.api.config.AppComponent;
import com.one.mvp.api.interaction.ApiInteractor;

/**
 * Created by swplzj on 17/5/2.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends LifecycleActivity{

  protected T mBinding;
  protected ApiInteractor api;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initLayout();
  }



  private void initLayout() {

    AppComponent appComponent = ((App) getApplication()).getAppComponent();
    api = appComponent.getApiInteractor();

    ViewDataBinding inflate = DataBindingUtil.setContentView(this, getLayoutId());
    mBinding = DataBindingUtil.bind(inflate.getRoot());
    if (mBinding == null) {
      if (getLayoutId() == 0) {
        throw new InflateException("activity no source_ID");
      } else {
        throw new NullPointerException("mBinding==NULL");
      }
    }
    setupView();
  }

  @LayoutRes
  protected abstract int getLayoutId();

  protected abstract void setupView();

}






















