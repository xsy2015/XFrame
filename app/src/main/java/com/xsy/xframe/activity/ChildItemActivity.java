package com.xsy.xframe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xsy.xframe.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2018/3/22
 */

public class ChildItemActivity extends Activity {

    @BindView(R.id.lv)
    ListView lv;

    private List mData=new ArrayList();
    private ChildItemOnclick childItemOnclick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_item);
        ButterKnife.bind(this);

        initData();
        initEvent();
    }



    private void initData() {
        for (int i = 0; i <4 ; i++) {
            mData.add("哈哈哈"+i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_child, mData);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initEvent() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              childItemOnclick.callBackOnclick(position);
              finish();
            }
        });
    }

    public void setChildItemOnclick(ChildItemOnclick childItemOnclick) {
        this.childItemOnclick = childItemOnclick;
    }
}
