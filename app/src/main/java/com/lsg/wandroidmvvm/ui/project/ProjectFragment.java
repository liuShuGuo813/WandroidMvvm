package com.lsg.wandroidmvvm.ui.project;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.lsg.wandroidmvvm.R;
import com.lsg.wandroidmvvm.base.BaseListFragment;
import com.lsg.wandroidmvvm.bean.PageList;
import com.lsg.wandroidmvvm.bean.ProjectArticleBean;
import com.lsg.wandroidmvvm.bean.ProjectTreeBean;
import com.lsg.wandroidmvvm.databinding.FragmentProjectBinding;
import com.lsg.wandroidmvvm.http.Urls;
import com.lsg.wandroidmvvm.ui.WebViewActivity;
import com.lsg.wandroidmvvm.util.CommonUtils;
import com.lsg.wandroidmvvm.util.StatusBarUtil;
import com.lsg.wandroidmvvm.widget.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsg on 2020/11/2
 */
public class ProjectFragment extends BaseListFragment<ProjectViewModel, FragmentProjectBinding> {

    private ProjectTitleAdapter mProjectTitleAdapter;
    private ProjectArticleAdapter mProjectArticleAdapter;
    private boolean isLoadMore;
    private int mPage;
    private int mCid;

    @Override
    protected void setListener() {
        mProjectTitleAdapter.setOnItemClickListener((adapter, view, position) -> {
            mCid = mProjectTitleAdapter.getData().get(position).getId();
            getArticleData(false);
        });
        mProjectArticleAdapter.setOnItemClickListener((adapter, view, position) -> {
            String url = mProjectArticleAdapter.getData().get(position).getLink();
            String title = mProjectArticleAdapter.getData().get(position).getTitle();
            WebViewActivity.loadUrl(activity,
                    url,
                    title);
        });

        bindView.refresh.setOnRefreshListener(refreshLayout -> {
            if(mCid == 0){
                getProjectTitleData();
            }else {
                getArticleData(false);
            }

        });

        bindView.refresh.setOnLoadMoreListener(refreshLayout -> {
            getArticleData(true);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void processLogic() {
        viewModel.setBaseListView(this);
        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        bindView.rcvTitle.setLayoutManager(new LinearLayoutManager(activity));
        bindView.rcvProject.setLayoutManager(new LinearLayoutManager(activity));
        mProjectTitleAdapter = new ProjectTitleAdapter(new ArrayList<ProjectTreeBean>());
        mProjectArticleAdapter = new ProjectArticleAdapter(new ArrayList<ProjectArticleBean>());
        bindView.rcvTitle.setAdapter(mProjectTitleAdapter);
        bindView.rcvProject.setAdapter(mProjectArticleAdapter);
    }

    private void initData() {
        getProjectTitleData();
    }

    private void getArticleData(boolean isLoad) {
        isLoadMore = isLoad;
        if(isLoad){
            mPage++;
        }else {
            mPage = 0;
        }
        viewModel.getProjectList(Urls.project_article_url, mPage,mCid)
                .observe(getViewLifecycleOwner(),observer);
        bindView.supLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);//隐藏拖动布局
    }

    private void updateArticleList(List<ProjectArticleBean> projectArticleBeanList) {
        if(!isLoadMore){
            mProjectArticleAdapter.getData().clear();
        }
        mProjectArticleAdapter.addData(projectArticleBeanList);
        if(isLoadMore){
            bindView.refresh.finishLoadMore();
        }else {
            bindView.refresh.finishRefresh();
        }
    }

    private Observer<PageList<ProjectArticleBean>> observer = articleBeanPageList -> {
        updateArticleList(articleBeanPageList.getDatas());
    };

    private void getProjectTitleData() {
        viewModel.getProjectTreeList(Urls.project_tree_url)
                .observe(getViewLifecycleOwner(), new Observer<List<ProjectTreeBean>>() {
                    @Override
                    public void onChanged(List<ProjectTreeBean> projectTreeBeanList) {
                        mProjectTitleAdapter.addData(projectTreeBeanList);
                        if(mProjectTitleAdapter != null && mProjectTitleAdapter.getData().size() > 0){
                            mCid = mProjectTitleAdapter.getData().get(0).getId();
                            getArticleData(false);
                        }
                    }
                });
    }

    public static ProjectFragment getInstance(){
        return new ProjectFragment();
    }

    @Override
    public void onFresh() {
        getProjectTitleData();
    }
}
