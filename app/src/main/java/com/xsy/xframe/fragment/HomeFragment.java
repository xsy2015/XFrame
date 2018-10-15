package com.xsy.xframe.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.xsy.base.ui.BaseFragment;
import com.xsy.base.ui.activity.DisplayH5PageActivity;
import com.xsy.xframe.R;
import com.xsy.xframe.adapter.NewsListAdapter;
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

    private List<NewsBean.DataBean> newsData = new ArrayList<>();
    private NewsListAdapter adapter;

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
        adapter = new NewsListAdapter(R.layout.item_health_consult, newsData, getContext());
        mRecyclerView.setAdapter(adapter);

        GankApi gankApi = DrakeetFactory.getGankIOSingleton();
        gankApi.getNewsList("1", "10")
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
                });
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), DisplayH5PageActivity.class);
                intent.putExtra("url", "http://www.jkbat.com/App/NewsDetail/" + newsData.get(position).getID());
                intent.putExtra("titleName", "资讯详情");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
