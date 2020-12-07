package com.lsg.wandroidmvvm.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.lsg.wandroidmvvm.BaseView;
import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.util.ClassUtil;
import com.lsg.wandroidmvvm.util.CommonUtils;
import com.lsg.wandroidmvvm.util.StatusBarUtil;
import com.lsg.wandroidmvvm.widget.CustomProgress;

/**
 * Created by lsg on 2020/10/28
 */
public abstract class BaseActivity<VM extends AndroidViewModel,VDB extends ViewDataBinding>
        extends AppCompatActivity implements BaseView {

    private static final String TAG = "BaseActivity";
    protected VM viewModel;
    protected VDB bindView;
    private Activity activity;
    private CustomProgress dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        bindView = DataBindingUtil.setContentView(this,getLayoutId());
        bindView.setLifecycleOwner(this);
        initStatusBar();
        initViewModel();
        processLogic();
        setListener();
    }


    /**
     * 设置透明状态栏,兼容4.4
     */
    private void initStatusBar() {
        StatusBarUtil.setColor(this, CommonUtils.getColor(R.color.colorPrimary),0);
    }

    private void initViewModel() {
        Class<VM> viewModelClass = ClassUtil.getViewModel(this);
        if(viewModelClass != null){
            this.viewModel = new ViewModelProvider(this).get(viewModelClass);
        }
    }

    /**
     * 展示加载框
     */
    @Override
    public void showLoading(){
        if (dialog == null) {
            dialog = CustomProgress.show(activity, "正在加载中...", true, null);
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void hideLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public Activity getAtContext() {
        return activity;
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

    /**
     * 禁止改变字体大小
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
