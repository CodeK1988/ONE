package com.one.mvp.ui.maintab.fragment;

import android.support.annotation.NonNull;

import com.one.mvp.R;
import com.one.mvp.base.BaseFragment;
import com.one.mvp.databinding.MainFragmentOneBinding;
import com.one.mvp.model.ViewPagerContract;
import com.one.mvp.presenter.ViewPagerPresenter;
import com.one.mvp.ui.maintab.OneFragmentAdapter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 *  一个 首页
 * Created by swplzj on 17/5/3.
 */

public class OneFragment extends BaseFragment<MainFragmentOneBinding> implements ViewPagerContract.View{



  @NonNull
  private ViewPagerContract.Presenter mPresenter;

  @Override
  protected int getLayoutId() {
    return R.layout.main_fragment_one;
  }

  @Override
  protected void setupView() {
    new ViewPagerPresenter(getActivity(),this);
  }
  /**
   *  view
   * @param oneFragmentAdapter
   */
  @Override
  public void setPagerAdapter(@NonNull OneFragmentAdapter oneFragmentAdapter) {
    inflate.viewPager.setAdapter(checkNotNull(oneFragmentAdapter));
  }







  @Override
  public void onResume() {
    super.onResume();
    mPresenter.subscribe();
  }

  @Override
  public void onPause() {
    super.onPause();
    mPresenter.unsubscribe();
  }

  @Override
  public void setPresenter(ViewPagerContract.Presenter presenter) {
    mPresenter = checkNotNull(presenter);
  }
}
