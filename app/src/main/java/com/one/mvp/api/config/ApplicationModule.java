package com.one.mvp.api.config;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swplzj on 17/5/3.
 */

@Module
public class ApplicationModule {

  Context mContext;

  ApplicationModule(Context context){
    mContext = context;
  }

  @AppScope
  @Provides
  Context provideContext(){
    return mContext;
  }

}
