package com.one.mvp.ui.maintab.activity;

import com.one.mvp.R;
import com.one.mvp.base.BaseActivity;
import com.one.mvp.databinding.HomeActBinding;
import com.one.mvp.presenter.MainTabPresenter;

/**
 * tab标签页面切换
 */
public class HomeActivity extends BaseActivity<HomeActBinding> {


  private MainTabPresenter presenter;

  @Override
  protected int getLayoutId() {
    return R.layout.home_act;
  }


  @Override
  protected void setupView() {
    if(presenter==null)
    presenter = new MainTabPresenter(this, mBinding.radio);
    presenter.switchTo();
  }
}
