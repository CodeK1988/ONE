package com.one.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.one.mvp.model.ViewPagerContract;
import com.one.mvp.ui.maintab.OneFragmentAdapter;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.one.mvp.App.getContext;

/**
 * Created by swplzj on 17/5/8.
 */

public class ViewPagerPresenter<T> implements ViewPagerContract.Presenter {

  @NonNull
  private final CompositeSubscription mSubscriptions;

  @NonNull
  private final OneFragmentAdapter oneFragmentAdapter;

  @NonNull
  private final ViewPagerContract.View mViewPagerContractView;

  @Nullable
  ArrayList mList;
  public ViewPagerPresenter(ViewPagerContract.View mView, @Nullable ArrayList<T> strings){
    mViewPagerContractView = checkNotNull(mView);
    mList = checkNotNull(strings);
    oneFragmentAdapter = new OneFragmentAdapter(getContext(), mList);
    mSubscriptions = new CompositeSubscription();
    mViewPagerContractView.setPresenter(this);
  }





  @Override
  public void subscribe() {
    Subscription subscribe = Observable.just(oneFragmentAdapter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(oneFragmentAdapter -> {
              mViewPagerContractView.getPagerAdapter(oneFragmentAdapter);
            });
    mSubscriptions.add(subscribe);
  }

  @Override
  public void unsubscribe() {
    mSubscriptions.clear();
  }
}
