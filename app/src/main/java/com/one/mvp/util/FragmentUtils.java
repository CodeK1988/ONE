package com.one.mvp.util;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by swplzj on 17/5/4.
 */

public class FragmentUtils {

  private final HashMap<String, Fragment> fragments = new HashMap<>();

  private final FragmentActivity activity;
  private final FragmentManager manager;
  private final int container;

  private String current;

  public FragmentUtils(FragmentActivity activity, @IdRes int container) {
    this.activity = activity;
    this.container = container;
    this.manager = activity.getSupportFragmentManager();
  }


  /**
   *  one add show
   * @param fragment
   */
  public void switchTo(Class<? extends Fragment> fragment) {
    String name = fragment.getName();
    if (null != current) {
      manager.beginTransaction().hide(fragments.get(current)).commit();
    }

    if (null == manager.findFragmentByTag(name)) {
      Fragment instance = Fragment.instantiate(activity, name);
      fragments.put(name, instance);
      manager.beginTransaction().add(container, instance, name).addToBackStack(name).commit();
    } else {
      manager.beginTransaction().show(fragments.get(name)).commit();
    }

    current = name;
  }

  @Nullable
  public Fragment getFragment(Class<? extends Fragment> fragment) {
    return fragments.get(fragment.getName());
  }

  /**
   *  two replace
   */
  public void openFragment(Fragment fragment){
    FragmentTransaction fragmentTransaction = manager
            .beginTransaction();
    fragmentTransaction.replace(container, fragment);
    fragmentTransaction.commit();
  }

  /**
   *
   * 单纯添加 用的比较少
   * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
   * performed by the {@code fragmentManager}.
   */
  public void addFragmentToActivity (@NonNull Fragment f) {
    Fragment fragment = checkNotNull(f);
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.add(container, fragment);
    transaction.commit();
  }
}