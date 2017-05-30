package com.one.mvp.ui.basicSample.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.mvp.R;
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
public class SampleReadingFragment extends LifecycleFragment {

  private MainFragmentOneBinding mBinding;
  private List<OneFragmentEntity.DataEntity> mList=null;
  private OneFragmentAdapter fragmentAdapter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment_one, container, false);

    fragmentAdapter = new OneFragmentAdapter(getActivity(), mList);
    mBinding.viewPager.setAdapter(fragmentAdapter);

    return mBinding.getRoot();
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    SampleReadingViewModel viewModel = ViewModelProviders.of(this).get(SampleReadingViewModel.class);
    subscribeUi(viewModel);
  }

  private void subscribeUi(SampleReadingViewModel viewModel) {
    viewModel.getData().observe(this, dataEntities -> {
      if(dataEntities!=null){
        mList = dataEntities;
        fragmentAdapter.setData(mList);
      }
    });
  }
}
