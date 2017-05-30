package com.one.mvp.api.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by swplzj on 17/5/25.
 */

public class AppRxSchedulers implements RxSchedulers {


  private static Executor backgroundExecutor = Executors.newCachedThreadPool();
  private static Scheduler BACKGROUND_SCHEDULERS = Schedulers.from(backgroundExecutor);
  private static Executor internetExecutor = Executors.newCachedThreadPool();
  public static Scheduler INTERNET_SCHEDULERS = Schedulers.from(internetExecutor);

  @Override
  public Scheduler runOnBackground() {
    return BACKGROUND_SCHEDULERS;
  }

  @Override
  public Scheduler io() {
    return Schedulers.io();
  }

  @Override
  public Scheduler computation() {
    return Schedulers.computation();
  }

  @Override
  public Scheduler mainThread() {
    return AndroidSchedulers.mainThread();
  }

  @Override
  public Scheduler internet() {
    return INTERNET_SCHEDULERS;
  }
}
