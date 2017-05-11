package com.one.mvp.base;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by swplzj on 17/5/2.
 */

public interface BaseView<T> {


  void setPresenter(T presenter);
}
