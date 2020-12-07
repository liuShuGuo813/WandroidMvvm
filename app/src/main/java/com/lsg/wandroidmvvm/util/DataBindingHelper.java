package com.lsg.wandroidmvvm.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.lsg.wandroidmvvm.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by lsg on 2020/11/3
 */
public class DataBindingHelper {

    @BindingAdapter("imageWithGlide")
    public static void loadImage(ImageView imageView,String url){
        Glide.with(imageView).load(url)
                .placeholder(R.drawable.image_holder)
                .error(R.drawable.image_holder)
                .transition(withCrossFade())
                .centerCrop()
                .into(imageView);
    }

}
