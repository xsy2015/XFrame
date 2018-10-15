package com.xsy.xframe.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.xsy.xframe.R;
import com.xsy.xframe.utils.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description描述:体质列表
 * @Author作者: xuesanyang
 * @Date日期: 2016/11/16
 */
public class TizhiListAdpter extends BaseAdapter {
    private Context mContext;
    private ViewHolder holder = null;
    private boolean		isClosed;
    public  Map<Integer, Boolean> isUnfold;// 用来表示是否是打开或是关闭

    public TizhiListAdpter(Context context) {
        this.mContext=context;
        init();
    }

    private void init() {
        isUnfold=new HashMap<>();
        for (int i = 0; i <10 ; i++) {//设置初始值
          isUnfold.put(i,true);
        }
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_tizhi_list, parent, false);
            holder = new ViewHolder();
            // 设置标记
            convertView.setTag(holder);
            holder.tv_title=(TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
            holder.holeLayout= (TextView) convertView.findViewById(R.id.holeLayout);
            holder.tv_solution=(TextView) convertView.findViewById(R.id.tv_solution);
            holder.tv_fangjie=(TextView) convertView.findViewById(R.id.tv_fangjie);
            holder.ll_fangjie_dec=(LinearLayout) convertView.findViewById(R.id.ll_fangjie_dec);
            holder.iv_arrow= (ImageView) convertView.findViewById(R.id.iv_arrow);
            holder.tv_unfold=(TextView) convertView.findViewById(R.id.tv_unfold);
            holder.ll_content= (LinearLayout) convertView.findViewById(R.id.ll_content);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        holder.ll_content.setTag(position);
        holder.ll_fangjie_dec.setTag(position);

        holder.ll_fangjie_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                isUnfold.put(pos,!isUnfold.get(pos));

                notifyDataSetChanged();

                LogUtils.i("pos="+pos);
            }
        });
        if (isUnfold.get(position)){
            holder.ll_content.setVisibility(View.VISIBLE);
            holder.tv_unfold.setText("收起详情");
            holder.iv_arrow.setImageResource(R.drawable.icon_close);
        }else {
            holder.ll_content.setVisibility(View.GONE);
            holder.tv_unfold.setText("展开详情");
            holder.iv_arrow.setImageResource(R.drawable.icon_open);
        }


        return convertView;
    }

    class ViewHolder{
        TextView tv_title;
        TextView tv_name;
        TextView holeLayout;//穴位
        TextView tv_solution;
        TextView tv_fangjie;
        LinearLayout ll_fangjie_dec;
        ImageView iv_arrow;//收缩展开图标
        TextView tv_unfold;//收缩展开
        LinearLayout ll_content;
    }

    private void change(int position){
        if (isUnfold.get(position)){
            holder.ll_content.setVisibility(View.VISIBLE);
            holder.tv_unfold.setText("收起详情");
            //holder.iv_arrow.setImageResource(R.drawable.icon_close);
            ObjectAnimator.ofFloat(holder.iv_arrow, "rotation", -180, 0).setDuration(1000).start();
        }else {
            holder.ll_content.setVisibility(View.GONE);
            holder.tv_unfold.setText("展开详情");
           // holder.iv_arrow.setImageResource(R.drawable.icon_open);
            ObjectAnimator.ofFloat(holder.iv_arrow, "rotation", 0,180).setDuration(1000).start();
        }
    }

    private void toggle(boolean animated)
    {

        if (!isClosed)
        {
            // 打开的----》 关闭
            // 由大的输变为小的数
            // textView高度---》 7行显示
            if (animated)
            {
                holder.tv_unfold.setText("收起详情");
            }
        }
        else
        {
            // 关闭的----》打开
            // 由小的输变为大的数
            if (animated)
            {
                holder.tv_unfold.setText("展开详情");
            }

        }

        // 箭头的变化
        if (!isClosed)
        {
            // 打开的----》 关闭
            if (animated)
            {
                ObjectAnimator.ofFloat(holder.iv_arrow, "rotation", 0,180).start();
            }
            else
            {
                holder.iv_arrow.setImageResource(R.drawable.icon_close);
            }
        }
        else
        {
            if (animated)
            {
                ObjectAnimator.ofFloat(holder.iv_arrow, "rotation", -180, 0).start();
            }
            else
            {
                holder.iv_arrow.setImageResource(R.drawable.icon_open);
            }
        }

        // 状态变化
        isClosed = !isClosed;

        System.out.println("isClosed="+isClosed);
    }
}
