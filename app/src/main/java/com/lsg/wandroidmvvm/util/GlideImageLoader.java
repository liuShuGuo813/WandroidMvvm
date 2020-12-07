package com.lsg.wandroidmvvm.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lsg.wandroidmvvm.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by lsg on 2020/10/19
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop().into(imageView);
    }
}
