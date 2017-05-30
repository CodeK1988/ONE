package com.one.mvp.ui.basicSample.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.one.mvp.R;
import com.one.mvp.ui.basicSample.fragment.SampleReadingFragment;
import com.one.mvp.util.FragmentUtils;

import hugo.weaving.DebugLog;

/**
 * Created by swplzj on 17/5/30.
 */
@DebugLog
public class BasicSampleActivity extends LifecycleActivity {

  private FragmentUtils fragmentUtil;

  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    setContentView(R.layout.basicsample_act);
    if (fragmentUtil == null)
      fragmentUtil = new FragmentUtils(this, R.id.fragment_container);
    fragmentUtil.switchTo(SampleReadingFragment.class);
  }


}
