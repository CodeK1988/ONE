package com.one.mvp.ui.basicSample.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.one.mvp.R;
import com.one.mvp.base.BaseFragment;
import com.one.mvp.databinding.MainFragmentOneBinding;
import com.one.mvp.ui.entity.OneFragmentEntity;
import com.one.mvp.ui.maintab.OneFragmentAdapter;
import com.one.mvp.viewmodel.SampleReadingViewModel;

import java.util.List;

import hugo.weaving.DebugLog;

/**
 * Created by swplzj on 17/5/30.
 */

@DebugLog
public class SampleReadingFragment extends BaseFragment<MainFragmentOneBinding> {

  private List<OneFragmentEntity.DataEntity> mList = null;
  private OneFragmentAdapter fragmentAdapter;

  @Override
  protected int getLayoutId() {
    return R.layout.main_fragment_one;
  }

  @Override
  protected void setupView() {
    fragmentAdapter = new OneFragmentAdapter(getActivity(), mList);
    inflate.viewPager.setAdapter(fragmentAdapter);
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    SampleReadingViewModel viewModel = ViewModelProviders.of(this).get(SampleReadingViewModel.class);
    subscribeUi(viewModel);
  }

  private void subscribeUi(SampleReadingViewModel viewModel) {
    viewModel.getData().observe(this, dataEntities -> {
      if (dataEntities != null) {
        mList = dataEntities;
        fragmentAdapter.setData(mList);
      }
    });
  }
}
