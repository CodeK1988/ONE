package com.one.mvp.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by swplzj on 17/5/3.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {


  protected T inflate;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    inflate = DataBindingUtil.inflate(inflater, getLayoutId(), null, false);
    setupView();
    return inflate.getRoot();
  }

  @LayoutRes
  protected abstract int getLayoutId();

  protected abstract void setupView();

}
