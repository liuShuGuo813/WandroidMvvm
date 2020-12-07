package com.lsg.wandroidmvvm.ui.GongZhong;

import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.base.BaseFragment;
import com.lsg.wandroidmvvm.base.NoViewModel;
import com.lsg.wandroidmvvm.databinding.FragmentPublicBinding;

/**
 * Created by lsg on 2020/11/2
 */
public class PublicFragment extends BaseFragment<NoViewModel, FragmentPublicBinding> {

    @Override
    protected void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_public;
    }

    @Override
    public void processLogic() {

    }

    public static PublicFragment getInstance(){
        return new PublicFragment();
    }
}

