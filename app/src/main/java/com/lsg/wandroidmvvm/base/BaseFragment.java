package com.lsg.wandroidmvvm.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.lsg.wandroidmvvm.BaseView;
import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.ui.home.HomeViewModel;
import com.lsg.wandroidmvvm.util.ClassUtil;
import com.lsg.wandroidmvvm.util.CommonUtils;
import com.lsg.wandroidmvvm.util.StatusBarUtil;
import com.lsg.wandroidmvvm.widget.CustomProgress;

/**
 * Created by lsg on 2020/10/28
 */
public abstract class BaseFragment<VM extends AndroidViewModel,VDB extends ViewDataBinding>
        extends Fragment implements BaseView {

    private static final String TAG = "BaseFragment";
    protected VM viewModel;
    protected VDB bindView;
    private Activity activity;
    private CustomProgress dialog;
    // fragment是否显示了
    protected boolean mIsVisible = false;

    @Override
    public void onAttach(Context context) {
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
        initViewModel();
        processLogic();
        setListener();
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadData() {
    }

    protected void onVisible() {
        loadData();
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
