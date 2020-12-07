package com.lsg.wandroidmvvm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.base.BaseActivity;
import com.lsg.wandroidmvvm.base.NoViewModel;
import com.lsg.wandroidmvvm.databinding.ActivitySplashBinding;
import com.lsg.wandroidmvvm.ui.main.MainActivity;
import com.lsg.wandroidmvvm.util.StatusBarUtil;

import me.wangyuwei.particleview.ParticleView;


/**
 * @Time : 2020/10/26
 * @Author : lsg
 * @Description : 闪屏页
 **/
public class SplashActivity extends BaseActivity<NoViewModel, ActivitySplashBinding> {

    @Override
    public void processLogic() {
        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
            finish();
            return;
        }

        bindView.pvLoading.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.screen_zoom_in,R.anim.screen_zoom_out);
                finish();
            }
        });
        bindView.pvLoading.startAnim();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

}
