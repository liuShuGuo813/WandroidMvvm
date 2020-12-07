package com.lsg.wandroidmvvm.ui.square;

import androidx.fragment.app.Fragment;

import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.base.BaseFragment;
import com.lsg.wandroidmvvm.base.NoViewModel;
import com.lsg.wandroidmvvm.databinding.FragmentSquareBinding;
import com.lsg.wandroidmvvm.ui.home.HomeFragment;

/**
 * Created by lsg on 2020/11/2
 */
public class SquareFragment extends BaseFragment<NoViewModel, FragmentSquareBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_square;
    }

    @Override
    public void processLogic() {

    }

    public static SquareFragment getInstance(){
        return new SquareFragment();
    }
}
