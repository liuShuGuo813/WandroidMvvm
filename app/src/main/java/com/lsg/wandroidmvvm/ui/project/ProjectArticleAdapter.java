package com.lsg.wandroidmvvm.ui.project;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.bean.ProjectArticleBean;
import com.lsg.wandroidmvvm.bean.ProjectTreeBean;
import com.lsg.wandroidmvvm.databinding.RvItemProjectArticleBinding;
import com.lsg.wandroidmvvm.databinding.RvItemProjectTitleBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by lsg on 2020/10/30
 */
public class ProjectArticleAdapter extends BaseQuickAdapter<ProjectArticleBean, BaseViewHolder> {
    private static final String TAG = "ProjectArticleAdapter";
    public ProjectArticleAdapter(@Nullable List<ProjectArticleBean> data) {
        super(R.layout.rv_item_project_article, data);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ProjectArticleBean item) {
        RvItemProjectArticleBinding binding = baseViewHolder.getBinding();
        binding.setProjectArticle(item);
    }

}
