package com.one.mvp.ui.maintab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.mvp.R;
import com.one.mvp.base.BasePagerAdapter;

import java.util.List;

/**
 * Created by swplzj on 17/5/11.
 */

public class OneFragmentAdapter<T> extends BasePagerAdapter {

  private List<T> mList;
  private Context mContext;

  public OneFragmentAdapter(Context context, List<T> list) {
    this.mContext = context;
    this.mList = list;
  }

  @Override
  protected Object getItem(int position) {
    return mList.get(position);
  }

  @Override
  protected View getView(Object object, View convertView, ViewGroup parent) {
    View inflate = LayoutInflater.from(mContext).inflate(R.layout.viewpager_main_one, parent, false);
    return inflate;
  }

  @Override
  public int getCount() {
    return mList.size();
  }
}

