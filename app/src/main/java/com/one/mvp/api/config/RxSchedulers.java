package com.one.mvp.api.config;

import rx.Scheduler;

/**
 * Created by swplzj on 17/5/25.
 */

public interface RxSchedulers {

  Scheduler runOnBackground();

  Scheduler io();

  Scheduler computation();

  Scheduler mainThread();

  Scheduler internet();
}














