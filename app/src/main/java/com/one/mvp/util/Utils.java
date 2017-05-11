package com.one.mvp.util;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.ViewDataBinding;
import android.os.Looper;
import android.support.annotation.LayoutRes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by swplzj on 17/5/8.
 */

public class Utils {


  public static void ensureChangeOnMainThread() {
    if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
      throw new IllegalStateException("You must only modify the ObservableList on the main thread.");
    }
  }

  /**
   * Helper to throw an exception when {@link android.databinding.ViewDataBinding#setVariable(int,
   * Object)} returns false.
   */
  public static void throwMissingVariable(ViewDataBinding binding, int bindingVariable, @LayoutRes int layoutRes) {
    Context context = binding.getRoot().getContext();
    Resources resources = context.getResources();
    String layoutName = resources.getResourceName(layoutRes);
    // Yeah reflection is slow, but this only happens when there is a programmer error.
    String bindingVariableName;
    try {
      bindingVariableName = getBindingVariableName(context, bindingVariable);
    } catch (Resources.NotFoundException e) {
      // Fall back to int
      bindingVariableName = "" + bindingVariable;
    }
    throw new IllegalStateException("Could not bind variable '" + bindingVariableName + "' in layout '" + layoutName + "'");
  }

  /**
   * Returns the name for the given binding variable int. Warning! This uses reflection so it
   * should <em>only</em> be used for debugging.
   *
   * @throws Resources.NotFoundException if the name cannot be found.
   */
  public static String getBindingVariableName(Context context, int bindingVariable) throws Resources.NotFoundException {
    try {
      return getBindingVariableByDataBinderMapper(bindingVariable);
    } catch (Exception e1) {
      try {
        return getBindingVariableByBR(context, bindingVariable);
      } catch (Exception e2) {
        throw new Resources.NotFoundException("" + bindingVariable);
      }
    }
  }


  /**
   * Attempt to get the name from a non-public method on the generated DataBinderMapper class.
   * This method does exactly what we want, but who knows if it will be there in future versions.
   */
  private static String getBindingVariableByDataBinderMapper(int bindingVariable) throws Exception {
    Class<?> dataBinderMapper = Class.forName("android.databinding.DataBinderMapper");
    Method convertIdMethod = dataBinderMapper.getDeclaredMethod("convertBrIdToString", int.class);
    convertIdMethod.setAccessible(true);
    Constructor constructor = dataBinderMapper.getDeclaredConstructor();
    constructor.setAccessible(true);
    Object instance = constructor.newInstance();
    Object result = convertIdMethod.invoke(instance, bindingVariable);
    return (String) result;
  }


  /**
   * Attempt to get the name by using reflection on the generated BR class. Unfortunately, we
   * don't know BR's package name so this may fail if it's not the same as the apps package name.
   */
  private static String getBindingVariableByBR(Context context, int bindingVariable) throws Exception {
    String packageName = context.getPackageName();
    Class BRClass = Class.forName(packageName + ".BR");
    Field[] fields = BRClass.getFields();
    for (Field field : fields) {
      int value = field.getInt(null);
      if (value == bindingVariable) {
        return field.getName();
      }
    }
    throw new Exception("not found");
  }
}
