package com.lsg.wandroidmvvm;

import android.app.Activity;

/**
 * Created by lsg on 2020/10/30
 * ViewModel与Activity视图交互接口
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    Activity getAtContext();

}
