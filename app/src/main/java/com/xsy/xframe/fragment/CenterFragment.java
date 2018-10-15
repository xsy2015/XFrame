package com.xsy.xframe.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.xsy.base.ui.BaseFragment;
import com.xsy.xframe.R;
import com.xsy.xframe.activity.ChildItemActivity;
import com.xsy.xframe.activity.ChildItemOnclick;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2016/10/10
 */
public class CenterFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragement_center;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        mTopBar.setTitle("me");
        mTopBar.setTitleGravity(Gravity.CENTER);
    }
}

