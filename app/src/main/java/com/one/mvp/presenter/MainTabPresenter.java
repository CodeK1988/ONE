package com.one.mvp.presenter;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
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

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * setOnPageScrollListener
 * setAdapter
 * <p>
 * Created by swplzj on 17/5/5.
 */

public class MainTabPresenter implements MainTabContract.Presenter {

  @NonNull
  private final RadioGroup radioGroup;

  @NonNull
  private final MainTabContract.View mMainTabContractView;

  @NonNull
  private final FragmentActivity mContext;

  @NonNull
  private CompositeSubscription mSubscriptions;
  private FragmentUtils fragmentUtil;

  public MainTabPresenter(@NonNull FragmentActivity mActivity, @NonNull MainTabContract.View view,@NonNull RadioGroup group) {//
    mContext = checkNotNull(mActivity, "FragmentActivity cannot be null!");
    mMainTabContractView = checkNotNull(view);
    radioGroup = checkNotNull(group, "RadioGroup cannot be null!");
    mSubscriptions = new CompositeSubscription();
    mMainTabContractView.setPresenter(this);
  }

  private void switchTo() {
    if(fragmentUtil==null)
    fragmentUtil = new FragmentUtils(mContext, R.id.view_container);
    Subscription subscription = RxRadioGroup.checkedChanges(radioGroup).subscribe(integer -> {
      fragmentUtil.switchTo(switchTo(integer));
    });
    mSubscriptions.add(subscription);
  }


  private Class switchTo(@IdRes Integer i) {
    Integer integer = checkNotNull(i, "switchTo IdRes cannot be null");
    switch (integer) {
      case R.id.home_one:
        return OneFragment.class;
      case R.id.home_reading:
        return ReadingFragment.class;
      case R.id.home_music:
        return MusicFragment.class;
      case R.id.home_movie:
        return MovieFragment.class;
      default:
        break;
    }
    return null;
  }


  @Override
  public void subscribe() {
    switchTo();
  }

  @Override
  public void unsubscribe() {
    mSubscriptions.clear();
  }
}





















