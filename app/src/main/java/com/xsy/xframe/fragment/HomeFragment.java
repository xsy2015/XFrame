package com.xsy.xframe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.xsy.base.ui.BaseFragment;
import com.xsy.base.ui.network.JsonDataResponse;
import com.xsy.base.ui.network.RxUtil;
import com.xsy.base.ui.network.WebFailAction;
import com.xsy.base.ui.network.WebSuccessAction;
import com.xsy.xframe.activity.DisplayH5PageActivity;
import com.xsy.xframe.R;
import com.xsy.xframe.adapter.NewsListAdapter;
import com.xsy.xframe.bean.ArticleListBean;
import com.xsy.xframe.bean.NewsBean;
import com.xsy.xframe.network.DrakeetFactory;
import com.xsy.xframe.network.GankApi;
import com.xsy.xframe.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2016/10/10
 */
public class HomeFragment extends BaseFragment {
    private static final String Tag = "HomeFragment";
    @BindView(R.id.collapsing_topbar_layout)
    QMUICollapsingTopBarLayout mCollapsingTopBarLayout;
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private List<NewsBean.DataBean> newsData = new ArrayList<>();
    private List<ArticleListBean.DataBean.DatasBean> articlesData = new ArrayList<>();
    private NewsListAdapter adapter;
    private int pageIndex = 0;
    private int pageSize = 10;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        initView();
        initTopBar();
        initEvent();
    }

    private void initTopBar() {
        mCollapsingTopBarLayout.setTitle("XFrame");
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NewsListAdapter(R.layout.item_article_list, articlesData, getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        refreshLayout.setOnRefreshListener(new com.scwang.smartrefresh.layout.listener.OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageIndex=0;
                getArticleList(true);
                refreshLayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new com.scwang.smartrefresh.layout.listener.OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex+=1;
                getArticleList(false);
                refreshLayout.finishLoadMore(2000);
            }
        });

        getArticleList(true);
    }

    private void getArticleList(final boolean refresh) {
        GankApi gankApi = DrakeetFactory.getGankIOSingleton();
       /* gankApi.getNewsList("1", "10")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsBean>() {
                    @Override
                    public void call(NewsBean newsBean) {
                        LogUtils.i(Tag, newsBean.toString());
                        if (newsData != null) {
                            newsData.clear();
                        }
                        newsData.addAll(newsBean.getData());
                        adapter.notifyDataSetChanged();
                    }
                });*/

        gankApi.getArticleList(pageIndex+"",pageSize+"")
                .compose(RxUtil.<JsonDataResponse<ArticleListBean.DataBean>>normalSchedulers())
                .subscribe(new WebSuccessAction<JsonDataResponse<ArticleListBean.DataBean>>() {
                    @Override
                    public void onSuccess(JsonDataResponse<ArticleListBean.DataBean> extendedResponse) {
                        if (refresh) {
                            if (articlesData != null) {
                                articlesData.clear();
                            }
                        }
                        articlesData.addAll(extendedResponse.getData().getDatas());
                        adapter.notifyDataSetChanged();
                    }
                },new WebFailAction());
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), DisplayH5PageActivity.class);
                intent.putExtra("url", articlesData.get(position).getLink());
                intent.putExtra("titleName", articlesData.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
