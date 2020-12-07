package com.lsg.wandroidmvvm.base;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.lsg.wandroidmvvm.BaseView;


/**
 * Created by lsg on 2020/10/28
 */
public class BaseViewModel extends AndroidViewModel {

    private Activity activity;
    private BaseView baseView;
    public void setBaseView(BaseView baseView) {
        activity = baseView.getAtContext();
        this.baseView = baseView;
    }

    public Activity getActivity() {
        return activity;
    }

    public void showLoading(){
        if(baseView != null){
            baseView.showLoading();
        }
    }

    public void  hideLoading(){
        if(baseView != null){
            baseView.hideLoading();
        }
    }

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
