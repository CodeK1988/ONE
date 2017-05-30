package com.one.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.one.mvp.App;
import com.one.mvp.api.config.ConstantApi;
import com.one.mvp.api.interaction.ApiInteractor;
import com.one.mvp.base.BaseSubscribe;
import com.one.mvp.model.ViewPagerContract;
import com.one.mvp.ui.entity.OneFragmentEntity;
import com.one.mvp.ui.maintab.OneFragmentAdapter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by swplzj on 17/5/8.
 */

public class ViewPagerPresenter implements ViewPagerContract.Presenter {


  @NonNull
  private final CompositeSubscription mSubscriptions;
  @NonNull
  private final ViewPagerContract.View mViewPagerContractView;
  @NonNull
  private final ApiInteractor api;
  @Nullable
  private final FragmentActivity mActivity;

  public ViewPagerPresenter(@NonNull FragmentActivity context, @NonNull ViewPagerContract.View mView){
    mActivity = checkNotNull(context);
    api = checkNotNull(App.getAppComponent().getApiInteractor());
    mViewPagerContractView = checkNotNull(mView);
    mSubscriptions = new CompositeSubscription();
    mViewPagerContractView.setPresenter(this);
  }


  @Override
  public void subscribe() {
    Subscription apiOneHome = api.getOneHome(ConstantApi.OneFragmentApi, new BaseSubscribe<String>() {
      @Override
      public void onSuccess(String result) {
        Log.d("首页接口", "onSuccess: " + result);
        if(result!=null){// // TODO: 17/5/25
          OneFragmentEntity entity = new Gson().fromJson(result, OneFragmentEntity.class);
          mViewPagerContractView.setPagerAdapter(new OneFragmentAdapter(mActivity,entity.getData()));
        }
      }
    });
    mSubscriptions.add(apiOneHome);
  }

  @Override
  public void unsubscribe() {
    mSubscriptions.clear();
  }
}
