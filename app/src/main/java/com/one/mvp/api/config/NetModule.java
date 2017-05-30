package com.one.mvp.api.config;

import com.one.mvp.api.interaction.ApiInteractor;
import com.one.mvp.api.interaction.ApiInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swplzj on 17/5/3.
 */

@Module
public class NetModule {

  private ServiceConfig mConfig;
  public NetModule(ServiceConfig configuration){
    this.mConfig = configuration;
  }

  @AppScope
  @Provides
  public ApiInteractor provideHomeInteractor(){
    return new ApiInteractorImpl(mConfig);
  }




}
