package com.lsg.wandroidmvvm.ui.home;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.base.BaseFragment;
import com.lsg.wandroidmvvm.bean.ArticleBean;
import com.lsg.wandroidmvvm.bean.BannerBean;
import com.lsg.wandroidmvvm.bean.PageList;
import com.lsg.wandroidmvvm.databinding.FragmentHomeBinding;
import com.lsg.wandroidmvvm.http.Urls;
import com.lsg.wandroidmvvm.ui.WebViewActivity;
import com.lsg.wandroidmvvm.util.GlideImageLoader;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsg on 2020/10/30
 */
public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> {

    private HomeArticleAdapter articleAdapter;
    private List<ArticleBean> articleBeanList = new ArrayList<>();
    private List<ArticleBean> topArticleList = new ArrayList<>();
    private int page = 0;
    private boolean isLoadMore = false;
    private List<BannerBean> bannerBeanList = new ArrayList<>();
    private static final String TAG = "HomeFragment";
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void processLogic() {
        viewModel.setBaseView(this);
        initBanner();
        initRecyclerView();
        initData();
    }

    private void initData() {
        getBanner();
        getTopArticleData();
        getArticleData(false);
    }

    private void getTopArticleData() {
        viewModel.getTopArticleList(Urls.top_article_url)
                .observe(getViewLifecycleOwner(), articleBeans -> {
                    topArticleList.addAll(articleBeans);
                    for (ArticleBean articleBean : topArticleList) {
                        articleBean.setTop(true);
                    }
                    articleAdapter.addData(topArticleList);
                });
    }

    private void getArticleData(boolean isLoad) {
        isLoadMore = isLoad;
        if(isLoad){
            page++;
        }else {
            page = 0;
        }
        viewModel.getArticleList(Urls.main_article_url,page, isLoad ? false : true)
                .observe(getViewLifecycleOwner(),observer);
    }

    private void updateArticleList(List<ArticleBean> articleBeans) {
        if(!isLoadMore){
            articleAdapter.getData().clear();
            articleAdapter.addData(topArticleList);
        }
        articleAdapter.addData(articleBeans);
        if(isLoadMore){
            bindView.refresh.finishLoadMore();
        }else {
            bindView.refresh.finishRefresh();
        }
    }


    private void initRecyclerView() {
        bindView.rcvArticle.setLayoutManager(new LinearLayoutManager(getActivity()));
        articleAdapter = new HomeArticleAdapter(articleBeanList);
        bindView.rcvArticle.setAdapter(articleAdapter);
    }

    private Observer<PageList<ArticleBean>> observer = articleBeanPageList -> {
        updateArticleList(articleBeanPageList.getDatas());
    };

    private void getBanner() {
        viewModel.getBanner(Urls.banner_url)
                .observe(this, this::updateBanner);
    }

    private void updateBanner(List<BannerBean> bannerBeans) {
        if(bannerBeans == null || bannerBeans.size() <= 0){
            return;
        }
        bannerBeanList.clear();
        bannerBeanList.addAll(bannerBeans);
        List<String> urls = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (BannerBean bannerBean : bannerBeans) {
            urls.add(bannerBean.getImagePath());
            titles.add(bannerBean.getTitle());
        }
        bindView.banner.setBannerTitles(titles);
        bindView.banner.setImages(urls);
        bindView.banner.start();
    }

    private void initBanner() {
        bindView.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        bindView.banner.setImageLoader(new GlideImageLoader());
        bindView.banner.setIndicatorGravity(BannerConfig.CENTER);
        bindView.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        bindView.banner.setBannerAnimation(Transformer.Default);
        bindView.banner.startAutoPlay();
        bindView.banner.setDelayTime(5000);
        bindView.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(bannerBeanList == null || bannerBeanList.size() <= 0){
                    return;
                }
                Object obj = bannerBeanList.get(position);
                if (obj instanceof BannerBean) {
                    BannerBean bean = (BannerBean) obj;
                    WebViewActivity.loadUrl(getActivity(),
                            bean.getUrl(),
                            bean.getTitle());
                }
            }
        });
    }

    @Override
    protected void setListener() {
        bindView.titleBar.setRightImageClick(view ->{
                    ToastUtils.showShort("搜索");
                });

        bindView.refresh.setOnRefreshListener(refreshLayout -> {
            getArticleData(false);
        });

        bindView.refresh.setOnLoadMoreListener(refreshLayout -> {
            getArticleData(true);
        });

        articleAdapter.setOnItemClickListener((adapter, view, position) -> {
            if(articleAdapter == null){
                return;
            }
            String url = articleAdapter.getData().get(position).getLink();
            String title = articleAdapter.getData().get(position).getTitle();
            WebViewActivity.loadUrl(getActivity(),
                    url,
                    title);
        });

    }

    public static HomeFragment getInstance(){
        return new HomeFragment();
    }
}
