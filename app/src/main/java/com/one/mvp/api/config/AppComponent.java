package com.one.mvp.api.config;

import com.one.mvp.App;
import com.one.mvp.api.interaction.ApiInteractor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by swplzj on 17/5/3.
 */
@Singleton
@Component( modules = {  NetModule.class,ApplicationModule.class })
public interface AppComponent {

  void inject(App app);
  ApiInteractor getApiInteractor();
}
