package com.one.mvp.ui.maintab.activity;

import com.one.mvp.R;
import com.one.mvp.base.BaseActivity;
import com.one.mvp.databinding.TestActBinding;
import com.one.mvp.ui.basicSample.fragment.SampleReadingFragment;
import com.one.mvp.util.FragmentUtils;

/**
 * 测试
 * Created by swplzj on 17/6/21.
 */

public class TestActivity extends BaseActivity<TestActBinding> {

  private FragmentUtils fragmentUtil;

  @Override
  protected int getLayoutId() {
    return R.layout.test_act;
  }

  @Override
  protected void setupView() {
    if (fragmentUtil == null)
      fragmentUtil = new FragmentUtils(this, R.id.view_container);
    fragmentUtil.switchTo(SampleReadingFragment.class);
  }
}
