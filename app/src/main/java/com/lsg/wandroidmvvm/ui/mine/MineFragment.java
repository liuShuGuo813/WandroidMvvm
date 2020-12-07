package com.lsg.wandroidmvvm.ui.mine;

import androidx.fragment.app.Fragment;

import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.base.BaseFragment;
import com.lsg.wandroidmvvm.base.NoViewModel;
import com.lsg.wandroidmvvm.databinding.FragmentMineBinding;
import com.lsg.wandroidmvvm.ui.home.HomeFragment;

/**
 * Created by lsg on 2020/11/2
 */
public class MineFragment extends BaseFragment<NoViewModel, FragmentMineBinding>{
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void processLogic() {

    }

    public static MineFragment getInstance(){
        return new MineFragment();
    }
}
