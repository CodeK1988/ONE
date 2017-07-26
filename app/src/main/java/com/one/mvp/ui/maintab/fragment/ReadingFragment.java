package com.one.mvp.ui.maintab.fragment;

import android.util.Log;

import com.one.mvp.R;
import com.one.mvp.base.BaseFragment;
import com.one.mvp.databinding.MainFragmentReadingBinding;
import com.one.mvp.util.FragmentUtils;

import hugo.weaving.DebugLog;

/**
 * Created by swplzj on 17/5/4.
 */
@DebugLog
public class ReadingFragment extends BaseFragment<MainFragmentReadingBinding> {


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

//    startActivity(new Intent(getActivity(), TestActivity.class));
    inflate.helloJni.setText(SayHello());

  }

  public native String SayHello();

  static {
    System.loadLibrary("helloJni");
  }

}
