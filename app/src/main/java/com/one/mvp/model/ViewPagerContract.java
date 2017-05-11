package com.one.mvp.model;

import com.one.mvp.base.BasePresenter;
import com.one.mvp.base.BaseView;
import com.one.mvp.ui.maintab.OneFragmentAdapter;


/**
 * Created by swplzj on 17/5/8.
 */

public class ViewPagerContract {


  public interface View extends BaseView<Presenter> {


    void getPagerAdapter(OneFragmentAdapter oneFragmentAdapter);
  }

  public interface Presenter extends BasePresenter {


  }

}
