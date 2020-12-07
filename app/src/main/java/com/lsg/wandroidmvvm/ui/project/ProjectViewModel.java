package com.lsg.wandroidmvvm.ui.project;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.lsg.wandroidmvvm.base.BaseListViewModel;
import com.lsg.wandroidmvvm.base.BaseObserver;
import com.lsg.wandroidmvvm.base.BaseViewModel;
import com.lsg.wandroidmvvm.bean.PageList;
import com.lsg.wandroidmvvm.bean.ProjectArticleBean;
import com.lsg.wandroidmvvm.bean.ProjectTreeBean;
import com.lsg.wandroidmvvm.ui.main.MainActivity;
import com.rxjava.rxlife.RxLife;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import rxhttp.wrapper.param.RxHttp;

/**
 * Created by lsg on 2020/11/3
 */
public class ProjectViewModel extends BaseListViewModel {
    final MutableLiveData<List<ProjectTreeBean>> projectTreeList = new MutableLiveData<>();
    final MutableLiveData<PageList<ProjectArticleBean>> projectList = new MutableLiveData<>();

    public ProjectViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<ProjectTreeBean>> getProjectTreeList(String url){
        RxHttp.get(url)
                .asResponseList(ProjectTreeBean.class)
                .to(RxLife.toMain((MainActivity)getActivity())) //页面销毁、自动关闭请求
                .subscribe(projectTreeBeanList -> {
                    projectTreeList.setValue(projectTreeBeanList);
                    loadFinished();
                },throwable -> {
                    projectTreeList.setValue(null);
                    loadFailed(throwable.getMessage());
                });
        return projectTreeList;
    }

    public MutableLiveData<PageList<ProjectArticleBean>> getProjectList(String url, int page, int cid){
        RxHttp.get(url,page,cid)
                .asResponsePageList(ProjectArticleBean.class)
                .observeOn(AndroidSchedulers.mainThread())   //控制下游在主线程执行
                .subscribe(new Observer<PageList<ProjectArticleBean>>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        showLoadingView();
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull PageList<ProjectArticleBean> projectArticleBeanPageList) {
                        if(projectArticleBeanPageList.getDatas() == null || projectArticleBeanPageList.getDatas().size() <= 0){
                            showNoContentView();
                            projectList.setValue(null);
                        }else {
                            projectList.setValue(projectArticleBeanPageList);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        showLoadErrorView(e.getMessage());
                        projectList.setValue(null);
                    }

                    @Override
                    public void onComplete() {
                        loadFinished();
                    }
                });
        return projectList;
    }


}
