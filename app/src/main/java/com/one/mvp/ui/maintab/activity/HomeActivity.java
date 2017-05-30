package com.one.mvp.ui.maintab.activity;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.one.mvp.R;
import com.one.mvp.base.BaseActivity;
import com.one.mvp.databinding.HomeActBinding;
import com.one.mvp.model.MainTabContract;
import com.one.mvp.presenter.MainTabPresenter;

import hugo.weaving.DebugLog;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * tab标签页面切换
 * hugo debug
 */
@DebugLog
public class HomeActivity extends BaseActivity<HomeActBinding> implements MainTabContract.View {


  /**
   * 绑定布局文件
   * @return
   */
  @Override
  protected int getLayoutId() {
    return R.layout.home_act;
  }

  /**
   * 创建Presenter
   */
  @Override
  protected void setupView() {
    new MainTabPresenter(this,this,mBinding.radio);
  }


  /**
   *  view 层接口
   */
  @NonNull
  private MainTabContract.Presenter mainTabPresenter;
  @Override
  public void setPresenter(MainTabContract.Presenter presenter) {
    mainTabPresenter = checkNotNull(presenter);
  }


  /**
   *  绑定生命周期
   */
  @Override
  protected void onResume() {
    super.onResume();
    mainTabPresenter.subscribe();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mainTabPresenter.unsubscribe();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mainTabPresenter.unsubscribe();
  }




  /**
   * 返回处理
   */
  private long beforeTime = 0L;
  @Override
  public void onBackPressed() {// thanks.==> by JohnTsai(mailto:johntsai.work@gmail.com) on 16/7/27.
    if(System.currentTimeMillis()-beforeTime<1000L) {
      finish();
      super.onBackPressed();
    }else{
      beforeTime = System.currentTimeMillis();
      Toast.makeText(this, "再按一次退出一个", Toast.LENGTH_SHORT).show();
    }
  }
}
