package com.lsg.wandroidmvvm.http;

/**
 * Created by lsg on 2020/10/28
 */
public interface NetWorkCallBack {

    void showLoading();

    void loadSuccess(Object object);

    void loadError(Throwable throwable);

    void loadFail(String msg);

    void hideLoading();

}
