package com.one.mvp;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.one.mvp.api.config.AppComponent;
import com.one.mvp.api.config.DaggerAppComponent;
import com.one.mvp.api.config.NetModule;
import com.one.mvp.api.config.RxModule;
import com.one.mvp.api.config.ServiceConfig;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;

import java.util.Locale;

import static com.one.mvp.api.config.ConstantApi.APISERVICE_CONFIG;
import static com.one.mvp.api.config.ConstantApi.appIdBugly;

/**
 * Created by swplzj on 17/5/2.
 */

public class App extends Application {


  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(base);
    Beta.installTinker();
  }

  @Override
  public void onCreate() {
    super.onCreate();

    context = getApplicationContext();
    ServiceConfig config = ServiceConfig.builder().dataSourceType(APISERVICE_CONFIG)
            .build();
    appComponent = DaggerAppComponent.builder().netModule(new NetModule(config)).rxModule(new RxModule()).build();


    /**
     *  热修复 及 热更新 todo
     */
    setStrictMode();
    // 设置是否开启热更新能力，默认为true
    Beta.enableHotfix = true;
    // 设置是否自动下载补丁
    Beta.canAutoDownloadPatch = true;
    // 设置是否提示用户重启
    Beta.canNotifyUserRestart = true;
    // 设置是否自动合成补丁
    Beta.canAutoPatch = true;

    Beta.betaPatchListener = new BetaPatchListener() {
      @Override
      public void onPatchReceived(String patchFileUrl) {
        Toast.makeText(getApplicationContext(), patchFileUrl, Toast.LENGTH_SHORT).show();
        Log.d("BetaPatchListener", "onPatchReceived: patchFileUrl=" + patchFileUrl);
      }

      @Override
      public void onDownloadReceived(long savedLength, long totalLength) {
        Toast.makeText(getApplicationContext(), String.format(Locale.getDefault(),
                "%s %d%%",
                Beta.strNotificationDownloading,
                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)), Toast.LENGTH_SHORT).show();
        Log.d("BetaPatchListener", "onDownloadReceived: savedLength=" + savedLength + ",totalLength=" + totalLength);
      }

      @Override
      public void onDownloadSuccess(String patchFilePath) {
        Toast.makeText(getApplicationContext(), patchFilePath, Toast.LENGTH_SHORT).show();
//                Beta.applyDownloadedPatch();
        Log.d("BetaPatchListener", "onDownloadSuccess: " + patchFilePath);
      }

      @Override
      public void onDownloadFailure(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Log.d("BetaPatchListener", "onDownloadFailure: " + msg);
      }

      @Override
      public void onApplySuccess(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Log.d("BetaPatchListener", "onApplySuccess: " + msg);
      }

      @Override
      public void onApplyFailure(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Log.d("BetaPatchListener", "onApplyFailure: " + msg);
      }

      @Override
      public void onPatchRollback() {
        Toast.makeText(getApplicationContext(), "onPatchRollback", Toast.LENGTH_SHORT).show();
        Log.d("BetaPatchListener", "onPatchRollback: ");
      }
    };

    long start = System.currentTimeMillis();
    // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId,调试时将第三个参数设置为true
    Bugly.init(this, appIdBugly, false);
    long end = System.currentTimeMillis();
    Log.e("init time--->", end - start + "ms");

  }


  @TargetApi(9)
  protected void setStrictMode() {
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
  }

  /**
   * 获取到全局Context对象
   */
  private static Context context;

  public static Context getContext() {
    return context;
  }

  /**
   * 调用api
   */
  protected static AppComponent appComponent;

  public static AppComponent getAppComponent() {
    return appComponent;
  }


  /**
   * 强制  sp  字体不随系统改变而改变
   *
   * @param newConfig
   */
  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    if (newConfig.fontScale != 1) {
      getResources();
    }
    super.onConfigurationChanged(newConfig);
  }

  @Override
  public Resources getResources() {
    Resources res = super.getResources();
    if (res.getConfiguration().fontScale != 1) {
      Configuration newConfig = new Configuration();
      newConfig.setToDefaults();
      res.updateConfiguration(newConfig, res.getDisplayMetrics());//默认
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        createConfigurationContext(newConfig);
      } else {
        res.updateConfiguration(newConfig, res.getDisplayMetrics());
      }
    }
    return res;
  }
}
