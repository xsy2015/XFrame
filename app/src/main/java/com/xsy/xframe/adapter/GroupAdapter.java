package com.xsy.xframe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xsy.xframe.R;
import com.xsy.xframe.bean.InfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2016/10/28
 */
public class GroupAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData=new ArrayList();

    public GroupAdapter(Context context,List list) {
        this.mContext=context;
        this.mData=list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = View.inflate(mContext, R.layout.item_group, null);

            holder=new ViewHolder();
            convertView.setTag(holder);

            holder.tv = (TextView) convertView.findViewById(R.id.tv);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }


        holder.tv.setText(mData.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView tv;

    }

}
