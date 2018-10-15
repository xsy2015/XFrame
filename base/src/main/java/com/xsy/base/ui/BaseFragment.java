package com.xsy.base.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2018/4/27
 */

public abstract class BaseFragment extends Fragment {

    /* 指定layout */
    public abstract int getLayoutId();

    /* 初始化界面 */
    public abstract void afterBindView(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        afterBindView(savedInstanceState);

        return rootView;
    }
}
