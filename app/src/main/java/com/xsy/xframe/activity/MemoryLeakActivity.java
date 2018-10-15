package com.xsy.xframe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2018/3/8
 */

public class MemoryLeakActivity extends Activity{

    private static class MyHandler extends Handler{
        private final WeakReference<MemoryLeakActivity> mActivity;

        private MyHandler(MemoryLeakActivity activity) {
            this.mActivity = new WeakReference<MemoryLeakActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MemoryLeakActivity memoryLeakActivity = mActivity.get();
            if (memoryLeakActivity!=null){

            }
        }
    }

    private final MyHandler handler=new MyHandler(this);

    private static final Runnable mRunnable=new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler.postDelayed(mRunnable,1000*60*10);
        finish();
    }
}
