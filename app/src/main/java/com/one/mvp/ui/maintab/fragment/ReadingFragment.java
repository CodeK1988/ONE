package com.one.mvp.ui.maintab.fragment;

import android.content.Intent;

import com.one.mvp.R;
import com.one.mvp.base.BaseFragment;
import com.one.mvp.ui.maintab.activity.TestActivity;
import com.one.mvp.util.FragmentUtils;

import hugo.weaving.DebugLog;

/**
 * Created by swplzj on 17/5/4.
 */
@DebugLog
public class ReadingFragment extends BaseFragment {


  private FragmentUtils fragmentUtil;

  @Override
  protected int getLayoutId() {
    return R.layout.main_fragment_reading;
  }

  @Override
  protected void setupView() { // 想在fragment中打开 fragment
//    if(fragmentUtil==null)
//    fragmentUtil = new FragmentUtils(getActivity(), R.id.view_container);
//    fragmentUtil.switchTo(SampleReadingFragment.class);

    startActivity(new Intent(getActivity(), TestActivity.class));
  }


}
