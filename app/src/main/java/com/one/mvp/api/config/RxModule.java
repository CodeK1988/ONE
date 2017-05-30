package com.one.mvp.api.config;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swplzj on 17/5/25.
 */

@Module
public class RxModule {

  @AppScope
  @Provides
  RxSchedulers provideRxSchedulers(){
    return new AppRxSchedulers();
  }

}
