package com.one.mvp.api.config;

import com.one.mvp.App;
import com.one.mvp.api.interaction.ApiInteractor;

import dagger.Component;

/**
 * Created by swplzj on 17/5/3.
 */
@AppScope
@Component( modules = {  NetModule.class, ApplicationModule.class, RxModule.class })
public interface AppComponent {

  void inject(App app);

  ApiInteractor getApiInteractor();

  RxSchedulers rxSchedulers();
}
