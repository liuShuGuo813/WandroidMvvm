package com.lsg.wandroidmvvm.base;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.lsg.wandroidmvvm.BaseListView;
import com.lsg.wandroidmvvm.BaseView;


/**
 * Created by lsg on 2020/10/28
 */
public class BaseListViewModel extends AndroidViewModel {

    private Activity activity;
    private BaseListView mBaseListView;
    public void setBaseListView(BaseListView baseListView) {
        activity = baseListView.getAtContext();
        this.mBaseListView = baseListView;
    }

    public Activity getActivity() {
        return activity;
    }

    public void showLoadingView(){
        if(mBaseListView != null){
            mBaseListView.showLoadingView();
        }
    }

    public void showBadNetworkView(){
        if(mBaseListView != null){
            mBaseListView.showBadNetworkView();
        }
    }

    public void showNoContentView(){
        if(mBaseListView != null){
            mBaseListView.showNoContentView();
        }
    }

    public void loadFinished(){
        if(mBaseListView != null){
            mBaseListView.loadFinished();
        }
    }

    public void showLoadErrorView(String tip){
        if(mBaseListView != null){
            mBaseListView.showLoadErrorView(tip);
        }
    }

    public void loadFailed(String tip){
        if(mBaseListView != null){
            mBaseListView.loadFailed(tip);
        }
    }

    public BaseListViewModel(@NonNull Application application) {
        super(application);
    }
}
