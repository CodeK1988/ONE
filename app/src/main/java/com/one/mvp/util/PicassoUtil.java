package com.one.mvp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.one.mvp.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by swplzj on 17/5/12.
 */

public class PicassoUtil {

  private static volatile Picasso picasso;


  private PicassoUtil() {
    throw new RuntimeException();
  }

  public PicassoUtil(Context context) {
    picasso = new Picasso.Builder(context).build();
  }


  public static void buildImage(@Nullable ImageView imageView, @Nullable String imagePath) {
    ImageView mImageView = checkNotNull(imageView);
    String imgPath = checkNotNull(imagePath);
    final Context context = mImageView.getContext();
    boolean online = NetworkUtils.isConnected(context);
    picasso.load(imgPath)
            .placeholder(R.color.colorWhite)
            .networkPolicy(online ? NetworkPolicy.NO_CACHE : NetworkPolicy.OFFLINE)
            .config(Bitmap.Config.RGB_565)
            .fit()
            .into(mImageView);
  }

  public static Picasso getPicasso(Context context) {
    if (picasso == null) {
      new PicassoUtil(context);
    }
    return picasso.with(context);
  }
}
