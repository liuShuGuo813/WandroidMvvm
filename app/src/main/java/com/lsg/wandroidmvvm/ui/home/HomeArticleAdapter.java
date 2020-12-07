package com.lsg.wandroidmvvm.ui.home;

import android.text.Html;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.bean.ArticleBean;
import com.lsg.wandroidmvvm.databinding.RvItemHomeArticleBinding;
import com.lsg.wandroidmvvm.util.FormatUtil;
import com.lsg.wandroidmvvm.util.PerfectClickListener;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by lsg on 2020/10/30
 */
public class HomeArticleAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {
    private static final String TAG = "HomeArticleAdapter";
    public HomeArticleAdapter(@Nullable List<ArticleBean> data) {
        super(R.layout.rv_item_home_article, data);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ArticleBean item) {
        RvItemHomeArticleBinding binding = baseViewHolder.getBinding();
        binding.setDataBean(item);
        if (item.getTags() != null && item.getTags().size() > 0) {
            binding.tvTag.setText(item.getTags().get(0).getName());
            binding.tvTag.setVisibility(View.VISIBLE);
            binding.tvTag.setOnClickListener(new PerfectClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
//                    KnowledgeArticleActivity.start(v.getContext(), item.getTags().get(0));
                }
            });
        } else {
            binding.tvTag.setVisibility(View.GONE);
        }
        binding.tvChapterName.setText(Html.fromHtml(FormatUtil.formatChapterName(item.getSuperChapterName(), item.getChapterName())));
    }

}
