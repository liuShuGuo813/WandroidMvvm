package com.lsg.wandroidmvvm.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.lsg.wandroidmvvm.BaseListView;
import com.lsg.wandroidmvvm.BaseView;
import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.util.ClassUtil;
import com.lsg.wandroidmvvm.util.CommonUtils;
import com.lsg.wandroidmvvm.util.PerfectClickListener;
import com.lsg.wandroidmvvm.util.StatusBarUtil;
import com.lsg.wandroidmvvm.widget.CustomProgress;

/**
 * @Time : 2020/10/29
 * @Author : lsg
 * @Description : 适用于长列表页面,遮盖布局
 **/
public abstract class BaseListFragment<VM extends AndroidViewModel,VDB extends ViewDataBinding>
        extends Fragment implements BaseListView {


    private static final String TAG = "BaseListFragment";
    protected VM viewModel;
    protected VDB bindView;
    protected Activity activity;
    private CustomProgress dialog;
    /**
     * Activity中由于服务器异常导致加载失败显示的布局。
     */
    private View loadErrorView;

    /**
     * Activity中由于网络异常导致加载失败显示的布局。
     */
    private View badNetWorkView;

    /**
     * Activity中当界面上没有任何内容时展示的布局。
     */
    private View noContentView;

    /**
     * Activity中显示加载等待的控件。
     */
    private ProgressBar loadingView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        View view = bindView.getRoot();
        bindView.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpView();
        initViewModel();
        processLogic();
        setListener();
        onFresh();
    }

    /**
     * 设置状态布局 加载 加载失败 无内容 无网络
     */
    private void setUpView() {
        View view = View.inflate(activity, R.layout.layout_lce,null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(
                0,
                ConvertUtils.dp2px(getTopMargins()),
                0,
                0
        );
        //不影响原有UI,相当于浮层效果
        activity.addContentView(view,layoutParams);
        loadingView = view.findViewById(R.id.loading);
        noContentView = view.findViewById(R.id.noContentView);
        badNetWorkView = view.findViewById(R.id.badNetworkView);
        loadErrorView = view.findViewById(R.id.loadErrorView);
        if(loadingView == null){
            LogUtils.e(TAG,"LoadingView is NUll");
        }
        if(noContentView == null){
            LogUtils.e(TAG,"NoContentView is NUll");
        }
        if(badNetWorkView == null){
            LogUtils.e(TAG,"BadNetWorkView is NUll");
        }
        if(loadErrorView == null){
            LogUtils.e(TAG,"LoadErrorView is NUll");
        }
        loadFinished();
    }

    /**
     * 当Activity中的加载内容服务器返回失败,通过此方法显示提示界面给用户。
     *
     * @param tip
     * 界面中的提示信息
     */
    @Override
    public void showLoadErrorView(String tip) {
        loadFinished();
        if (loadErrorView != null) {
            TextView errorText = loadErrorView.findViewById(R.id.loadErrorText);
            errorText.setText(tip);
            loadErrorView.setVisibility(View.VISIBLE);
            loadErrorView.setOnClickListener(new PerfectClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    onFresh();
                }
            });
        }
    }

    /**
     * 当Activity中的内容因为网络原因无法显示的时候,通过此方法显示提示界面给用户。
     *
     * 重新加载点击事件回调
     */
    @Override
    public void showBadNetworkView() {
        loadFinished();
        badNetWorkView.setVisibility(View.VISIBLE);
        badNetWorkView.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                onFresh();
            }
        });
    }

    /**
     * 当Activity列表无内容,通过此方法显示提示界面给用户。
     *
     * 重新加载点击事件回调
     */
    @Override
    public void showNoContentView() {
        loadFinished();
        noContentView.setVisibility(View.VISIBLE);
        noContentView.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                onFresh();
            }
        });
    }

    @Override
    @CallSuper
    public void showLoadingView(){
        hideBadNetWorkView();
        hideLoadErrorView();
        hideNoContentView();
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    @CallSuper
    public void loadFinished(){
        hideNoContentView();
        hideLoadErrorView();
        hideBadNetWorkView();
        loadingView.setVisibility(View.GONE);
    }

    @Override
    @CallSuper
    public void loadFailed(String msg){
        hideNoContentView();
        hideLoadErrorView();
        hideBadNetWorkView();
        loadingView.setVisibility(View.GONE);
    }

    public void hideLoadErrorView(){
        loadErrorView.setVisibility(View.GONE);
    }

    public void hideBadNetWorkView(){
        badNetWorkView.setVisibility(View.GONE);
    }

    public void hideNoContentView(){
        noContentView.setVisibility(View.GONE);
    }

    /**
     * 根据屏幕方向改变TitleBar高度
     * @return
     */
    private float getTopMargins(){
        float margins;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            margins = 50f;
        }else {
            margins = 55f;
        }
        return margins;
    }


    private void initViewModel() {
        Class<VM> viewModelClass = ClassUtil.getViewModel(this);
        if(viewModelClass != null){
            this.viewModel = new ViewModelProvider(this).get(viewModelClass);
        }
    }

    @Override
    public void showLoading() {
        if (dialog == null) {
            dialog = CustomProgress.show(activity, "正在加载中...", true, null);
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public Activity getAtContext() {
        return getActivity();
    }

    /**
     * 重新刷新接口
     */
    public void onFresh(){

    }

    /**
     * 处理页面监听事件
     */
    protected void setListener(){

    }

    /**
     * 获取布局资源Id
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 处理逻辑
     */
    public abstract void processLogic();


}
