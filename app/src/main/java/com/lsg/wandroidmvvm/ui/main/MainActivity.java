package com.lsg.wandroidmvvm.ui.main;

import android.view.View;

import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.base.BaseActivity;
import com.lsg.wandroidmvvm.base.NoViewModel;
import com.lsg.wandroidmvvm.databinding.ActivityMainBinding;
import com.lsg.wandroidmvvm.listener.MyPageListener;

/**
 * Created by lsg on 2020/10/26
 */
public class MainActivity extends BaseActivity<NoViewModel, ActivityMainBinding> implements View.OnClickListener {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void processLogic() {
        initViewPager();
    }

    private void initViewPager() {
        MyFragmentPageAdapter fragmentPageAdapter = new MyFragmentPageAdapter(getSupportFragmentManager());
        bindView.vp.setAdapter(fragmentPageAdapter);
        //设置最大缓存页面
        bindView.vp.setOffscreenPageLimit(1);
        bindView.vp.setOnPageChangeListener(new MyPageListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setCurrentItem(0);
                        break;
                    case 1:
                        setCurrentItem(1);
                        break;
                    case 2:
                        setCurrentItem(2);
                        break;
                    case 3:
                        setCurrentItem(3);
                        break;
                    case 4:
                        setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });
        setCurrentItem(0);
    }


    private void setCurrentItem(int position) {
        boolean isHome = false;
        boolean isProject = false;
        boolean isSquare = false;
        boolean isPublic = false;
        boolean isMine = false;
        switch (position){
            case 0:
                isHome = true;
                break;
            case 1:
                isProject = true;
                break;
            case 2:
                isSquare = true;
                break;
            case 3:
                isPublic = true;
                break;
            case 4:
                isMine = true;
                break;
            default:
                isHome = true;
                break;
        }
        bindView.llHome.setSelected(isHome);
        bindView.llProject.setSelected(isProject);
        bindView.llSquare.setSelected(isSquare);
        bindView.llPublic.setSelected(isPublic);
        bindView.llMine.setSelected(isMine);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_home:
                bindView.vp.setCurrentItem(0);
                break;
            case R.id.ll_project:
                bindView.vp.setCurrentItem(1);
                break;
            case R.id.ll_square:
                bindView.vp.setCurrentItem(2);
                break;
            case R.id.ll_public:
                bindView.vp.setCurrentItem(3);
                break;
            case R.id.ll_mine:
                bindView.vp.setCurrentItem(4);
                break;
            default:
                break;
        }
    }
}
