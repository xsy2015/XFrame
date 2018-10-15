package com.xsy.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.ButterKnife;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2018/4/27
 */

public abstract class BaseFragmentActivity extends FragmentActivity {

    public abstract int getLayoutId();
    public abstract void afterBindView(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        afterBindView(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
    }
}
