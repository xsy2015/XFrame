package com.xsy.xframe.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xsy.xframe.R;
import com.xsy.xframe.bean.NewsBean;

import java.util.List;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2018/5/8
 */

public class NewsListAdapter extends BaseQuickAdapter<NewsBean.DataBean, BaseViewHolder> {
    private Context mContext;
    public NewsListAdapter(int layoutResId, @Nullable List<NewsBean.DataBean> data, Context mContext) {
        super(layoutResId, data);
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean item) {
       helper.setText(R.id.tv_title,item.getTitle());
       helper.setText(R.id.tv_content,item.getBody());
       Glide.with(mContext).load(item.getMainImage()).into((ImageView) helper.getView(R.id.iv_icon));
    }
}
