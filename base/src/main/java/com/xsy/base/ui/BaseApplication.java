package com.xsy.base.ui;

import android.app.Application;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2018/10/15
 */

public class BaseApplication extends Application {
    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
