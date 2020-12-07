package com.lsg.wandroidmvvm.http;

import com.lsg.wandroidmvvm.base.BaseResponse;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

/**
 * Created by lsg on 2020/10/29
 */
public abstract class LoadingOberver<T> extends DisposableObserver<BaseResponse<T>> {


    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onNext(@NonNull BaseResponse<T> tBaseResponse) {
        onSuccess(tBaseResponse.getData());
    }

    @Override
    public void onComplete() {

    }

    /**
     * 回调正常数据
     *
     * @param data
     */
    protected abstract void onSuccess(T data);


}
