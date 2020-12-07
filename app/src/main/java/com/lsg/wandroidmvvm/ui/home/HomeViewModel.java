package com.lsg.wandroidmvvm.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.lsg.wandroidmvvm.base.BaseObserver;
import com.lsg.wandroidmvvm.base.BaseViewModel;
import com.lsg.wandroidmvvm.bean.ArticleBean;
import com.lsg.wandroidmvvm.bean.BannerBean;
import com.lsg.wandroidmvvm.bean.PageList;
import com.lsg.wandroidmvvm.ui.main.MainActivity;
import com.rxjava.rxlife.RxLife;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.wrapper.param.RxHttp;


/**
 * Created by lsg on 2020/10/29
 */
public class HomeViewModel extends BaseViewModel {
    final MutableLiveData<List<BannerBean>> bannerData = new MutableLiveData<>();
    final MutableLiveData<PageList<ArticleBean>> articleList = new MutableLiveData<>();
    final MutableLiveData<List<ArticleBean>> topArticleList = new MutableLiveData<>();


    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<ArticleBean>> getTopArticleList(String url){
        RxHttp.get(url)
                .asResponseList(ArticleBean.class)
                .to(RxLife.toMain((MainActivity)getActivity())) //页面销毁、自动关闭请求
                .subscribe(articleBeanList -> {
                    topArticleList.setValue(articleBeanList);
                },throwable -> {
                    topArticleList.setValue(null);
                });
        return topArticleList;
    }

    public MutableLiveData<List<BannerBean>> getBanner(String url){
        RxHttp.get(url)
                .asResponseList(BannerBean.class)
                .to(RxLife.toMain((MainActivity)getActivity())) //页面销毁、自动关闭请求
                .subscribe(bannerBeans -> {
                    bannerData.setValue(bannerBeans);
                },throwable -> {
                    bannerData.setValue(null);
                });
        return bannerData;
    }

    public MutableLiveData<PageList<ArticleBean>> getArticleList(String url,int page){
        return getArticleList(url,page,true);
    }

    public MutableLiveData<PageList<ArticleBean>> getArticleList(String url,int page,boolean isLoading){
        RxHttp.get(url,page)
                .asResponsePageList(ArticleBean.class)
                .observeOn(AndroidSchedulers.mainThread())   //控制下游在主线程执行
                .subscribe(new BaseObserver<PageList<ArticleBean>>(getActivity(),isLoading) {
                    @Override
                    protected void onSuccess(PageList<ArticleBean> result) {
                        articleList.setValue(result);
                    }

                    @Override
                    protected void onFail(Throwable throwable) {
                        articleList.setValue(null);
                    }
                });
        return articleList;
    }

}
