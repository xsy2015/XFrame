package com.xsy.xframe.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xsy.xframe.R;
import com.xsy.xframe.bean.ArticleListBean;
import com.xsy.xframe.db.NotesBean;
import com.xsy.xframe.utils.DateUtils;

import java.util.List;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2018/5/8
 */

public class NotesListAdapter extends BaseQuickAdapter<NotesBean, BaseViewHolder> {
    private Context mContext;
    public NotesListAdapter(int layoutResId, @Nullable List<NotesBean> data, Context mContext) {
        super(layoutResId, data);
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, NotesBean item) {
       helper.setText(R.id.tv_title,item.getText());
        helper.setText(R.id.tv_content,item.getComment());
        helper.setText(R.id.tv_time, DateUtils.dateToString(item.getDate()));
    }
}
