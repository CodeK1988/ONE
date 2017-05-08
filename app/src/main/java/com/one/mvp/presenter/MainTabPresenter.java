package com.one.mvp.presenter;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;

import com.jakewharton.rxbinding.widget.RxRadioGroup;
import com.one.mvp.R;
import com.one.mvp.model.MainTabContract;
import com.one.mvp.ui.maintab.fragment.MovieFragment;
import com.one.mvp.ui.maintab.fragment.MusicFragment;
import com.one.mvp.ui.maintab.fragment.OneFragment;
import com.one.mvp.ui.maintab.fragment.ReadingFragment;
import com.one.mvp.util.FragmentUtils;

import rx.functions.Action1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by swplzj on 17/5/5.
 */

public class MainTabPresenter implements MainTabContract.Presenter {


  private RadioGroup radioGroup;
  private final FragmentActivity mContext;

  public MainTabPresenter(@Nullable FragmentActivity mActivity, @Nullable RadioGroup group){
    mContext = checkNotNull(mActivity, "FragmentActivity cannot be null!");
    radioGroup = checkNotNull(group, "RadioGroup cannot be null!");
  }

  @Override
  public void switchTo() {
    FragmentUtils fragmentUtil = new FragmentUtils(mContext, R.id.view_container);
    fragmentUtil.switchTo(OneFragment.class);
    RxRadioGroup.checkedChanges(radioGroup).subscribe(new Action1<Integer>() {
      @Override
      public void call(Integer integer) {
        switch (integer) {
          case R.id.home_one:
            fragmentUtil.switchTo(OneFragment.class);
            break;
          case R.id.home_reading:
            fragmentUtil.switchTo(ReadingFragment.class);
            break;
          case R.id.home_music:
            fragmentUtil.switchTo(MusicFragment.class);
            break;
          case R.id.home_movie:
            fragmentUtil.switchTo(MovieFragment.class);
            break;
          default:
            break;
        }
      }
    });
  }

  @Override
  public void start() {//有些地方 RxJava MediaPlayer Handler 等生命周期用得上

  }
}





















