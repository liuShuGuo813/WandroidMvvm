package com.lsg.wandroidmvvm.ui.project;

import android.text.Html;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.bean.ArticleBean;
import com.lsg.wandroidmvvm.bean.ProjectTreeBean;
import com.lsg.wandroidmvvm.databinding.RvItemProjectTitleBinding;
import com.lsg.wandroidmvvm.util.FormatUtil;
import com.lsg.wandroidmvvm.util.PerfectClickListener;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by lsg on 2020/10/30
 */
public class ProjectTitleAdapter extends BaseQuickAdapter<ProjectTreeBean, BaseViewHolder> {
    private static final String TAG = "ProjectTitleAdapter";
    public ProjectTitleAdapter(@Nullable List<ProjectTreeBean> data) {
        super(R.layout.rv_item_project_title, data);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ProjectTreeBean item) {
        RvItemProjectTitleBinding binding = baseViewHolder.getBinding();
        binding.setTitleBean(item);
    }

}
