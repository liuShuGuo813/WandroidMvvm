package com.lsg.wandroidmvvm.base;


import android.app.Activity;

import com.blankj.utilcode.util.NetworkUtils;
import com.lsg.wandroidmvvm.widget.CustomProgress;
import com.lsg.wandroidmvvm.http.ExceptionHandler;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * create by lsg in 2019/12/23
 **/
public abstract class BaseObserver<T> implements Observer<T> {
    /**
     * 控制请求是否显示加载框
     */
    private boolean isShowLoading = true;
    private CustomProgress dialog;
    private Activity activity;


    public BaseObserver(Activity activity) {
        this.activity = activity;
    }

    public BaseObserver(Activity activity,boolean flag) {
        this.activity = activity;
        this.isShowLoading = flag;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if(NetworkUtils.isConnected()){
            if(isShowLoading){
                if (dialog == null) {
                    dialog = CustomProgress.show(activity, "正在加载中...", true, null);
                }
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            }
        }
    }

    @Override
    public void onNext(T baseResponse) {
        hideLoading();
        onSuccess(baseResponse);
    }

    /**
     * 回调正常数据
     *
     * @param data
     */
    protected abstract void onSuccess(T data);


    protected abstract void onFail(Throwable throwable);



    @Override
    public void onError(Throwable e) {
        ExceptionHandler.handleException(e);
        onFail(e);
        hideLoading();
    }

    @Override
    public void onComplete() {
        hideLoading();
    }

    private void hideLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
