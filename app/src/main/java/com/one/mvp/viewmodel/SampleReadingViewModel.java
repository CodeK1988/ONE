package com.one.mvp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.one.mvp.App;
import com.one.mvp.api.config.ConstantApi;
import com.one.mvp.api.interaction.ApiInteractor;
import com.one.mvp.base.BaseSubscribe;
import com.one.mvp.ui.entity.OneFragmentEntity;

import java.util.List;

/**
 * Created by swplzj on 17/5/30.
 */

public class SampleReadingViewModel extends AndroidViewModel {

  final MutableLiveData<List<OneFragmentEntity.DataEntity>> data = new MutableLiveData<>();

  public SampleReadingViewModel(Application app) {// todo 注意这里的Application  不能改为集成的 App
    super(app);
    ApiInteractor api = App.getAppComponent().getApiInteractor();
    api.getOneHome(ConstantApi.OneFragmentApi, new BaseSubscribe<String>() {
      @Override
      public void onSuccess(String result) {
        if (result != null) {// // TODO: 17/5/25
          OneFragmentEntity entity = new Gson().fromJson(result, OneFragmentEntity.class);
          data.setValue(entity.getData());
        }
      }
    });
  }

  public LiveData<List<OneFragmentEntity.DataEntity>> getData() {
    return data;
  }


}
