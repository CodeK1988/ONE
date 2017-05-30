package com.one.mvp.ui.maintab;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.minimize.android.rxrecycleradapter.RxDataSource;
import com.one.mvp.R;
import com.one.mvp.base.BasePagerAdapter;
import com.one.mvp.databinding.ItemOnefragmentAdapterBinding;
import com.one.mvp.databinding.ViewpagerMainOneBinding;
import com.one.mvp.ui.entity.OneFragmentEntity;
import com.one.mvp.util.PicassoUtil;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by swplzj on 17/5/11.
 */

public class OneFragmentAdapter<T> extends BasePagerAdapter {

  @Nullable
  private List<OneFragmentEntity.DataEntity> mList;
  @NonNull
  private FragmentActivity mContext;
  private final PicassoUtil picassoUtil;

  public OneFragmentAdapter(@NonNull FragmentActivity fragmentActivity, @Nullable List<OneFragmentEntity.DataEntity> list) {
    mContext = checkNotNull(fragmentActivity);
    this.mList = list;
    picassoUtil = new PicassoUtil(mContext);
  }


  public void setData(List<OneFragmentEntity.DataEntity> list){
    this.mList = list;
    notifyDataSetChanged();
  }



  @Override
  protected Object getItem(int position) {
    return mList.get(checkElementIndex(position,mList.size()));
  }

  @Override
  protected View getView(Object object, View convertView, ViewGroup parent) {
    OneFragmentEntity.DataEntity entity = (OneFragmentEntity.DataEntity) object;
    ViewpagerMainOneBinding binding = DataBindingUtil.inflate(mContext.getLayoutInflater(), R.layout.viewpager_main_one, null, false);
    List<OneFragmentEntity.DataEntity> entityList = new ArrayList<>();
    entityList.add(entity);


    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
      @Override
      public boolean canScrollVertically() {
        return false;
      }
    };
    binding.recyclerView.setLayoutManager(linearLayoutManager);


    RxDataSource<OneFragmentEntity.DataEntity> rxDataSource = new RxDataSource<>(entityList);
    rxDataSource.repeat(5).<ItemOnefragmentAdapterBinding>bindRecyclerView(binding.recyclerView, R.layout.item_onefragment_adapter)
            .subscribe(viewHolder -> {
              ItemOnefragmentAdapterBinding dataBinding = viewHolder.getViewDataBinding();//获取到页面数据
              OneFragmentEntity.DataEntity item = viewHolder.getItem();//集合里面的数据
              picassoUtil.buildImage(dataBinding.image,item.getHp_img_url());
              dataBinding.tvContent.setText(item.getHp_content());
              dataBinding.tvContent.setTextColor(Color.parseColor(item.getContent_bgcolor()));
              dataBinding.tvAuthor.setText(item.getHp_author());
              dataBinding.tvAuthor.setTextColor(Color.parseColor(item.getContent_bgcolor()));
            });
    return binding.getRoot();
  }

  @Override
  public int getCount() {
    return mList==null?0:mList.size();
  }
}

