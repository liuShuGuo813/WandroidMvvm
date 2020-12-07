package com.lsg.wandroidmvvm;

import android.app.Activity;

/**
 * Created by lsg on 2020/10/30
 * ViewModel与BaseListActivity/BaseFragment视图交互接口
 */
public interface BaseListView {

    void showLoadErrorView(String tip);
    void showBadNetworkView();
    void showNoContentView();
    void showLoadingView();
    void loadFinished();
    void loadFailed(String msg);

    void showLoading();

    void hideLoading();

    Activity getAtContext();

}
