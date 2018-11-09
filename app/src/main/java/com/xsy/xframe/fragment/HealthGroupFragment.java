package com.xsy.xframe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.xsy.base.ui.BaseFragment;
import com.xsy.base.ui.network.JsonDataResponse;
import com.xsy.base.ui.network.RxUtil;
import com.xsy.base.ui.network.WebFailAction;
import com.xsy.base.ui.network.WebSuccessAction;
import com.xsy.xframe.R;
import com.xsy.xframe.adapter.TizhiListAdpter;
import com.xsy.xframe.bean.ArticleListBean;
import com.xsy.xframe.bean.BannerBean;
import com.xsy.xframe.bean.NewsBean;
import com.xsy.xframe.network.DrakeetFactory;
import com.xsy.xframe.network.GankApi;
import com.xsy.xframe.utils.LogUtils;
import com.xsy.xsy_widges.viewpager.MZBannerView;
import com.xsy.xsy_widges.viewpager.MZHolderCreator;
import com.xsy.xsy_widges.viewpager.MZViewHolder;

import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;


/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2016/10/10
 */
public class HealthGroupFragment extends BaseFragment {

    private static final java.lang.String TAG = "HealthGroupFragment";
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.news_banner)
    MZBannerView news_banner;
    public static HealthGroupFragment newInstance() {
        Bundle args = new Bundle();
        HealthGroupFragment fragment = new HealthGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_health_group;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        mTopBar.setTitle("photo");
        mTopBar.setTitleGravity(Gravity.CENTER);
        news_banner.setIndicatorVisible(false);
        initView();
    }

    private void initView() {
        GankApi gankApi = DrakeetFactory.getGankIOSingleton();
        gankApi.getBannerList()
                .compose(RxUtil.<JsonDataResponse<List<BannerBean.DataBean>>>normalSchedulers())
                .subscribe(new WebSuccessAction<JsonDataResponse<List<BannerBean.DataBean>>>() {
                    @Override
                    public void onSuccess(JsonDataResponse<List<BannerBean.DataBean>> response) {
                        LogUtils.i(TAG,"data="+response.getData());
                        updateUI(response);
                    }
                }, new WebFailAction());
    }

    private void updateUI(JsonDataResponse<List<BannerBean.DataBean>> datas) {
        news_banner.setPages(datas.getData(), new MZHolderCreator<NewBannerViewHolder>() {

            @Override
            public NewBannerViewHolder createViewHolder() {
                return new NewBannerViewHolder();
            }
        });
    }

    class NewBannerViewHolder implements MZViewHolder<BannerBean.DataBean> {

        private ImageView iv_news;
        private TextView tv_title_news;
        private CardView card_view;

        @Override
        public View createView(Context context) {
            View view = View.inflate(getActivity(), R.layout.item_news_pager, null);
            iv_news = (ImageView) view.findViewById(R.id.iv);
            tv_title_news = (TextView) view.findViewById(R.id.tv_title);
            card_view = (CardView) view.findViewById(R.id.card_view);

            return view;
        }

        @Override
        public void onBind(Context context, int position, BannerBean.DataBean dataBean) {
            if (dataBean != null) {
                Glide.with(context).load(dataBean.getImagePath()).into((iv_news));
                tv_title_news.setText(dataBean.getTitle());
            }
        }
    }
}
