package com.xsy.xsy_widges;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2018/8/23
 */

public class MyViewPage extends ViewGroup {

    public MyViewPage(Context context) {
        super(context);
    }

    public MyViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i <childCount ; i++) {
            getChildAt(i).layout(i*getWidth(),0,(i+1)*getWidth(),getHeight());
        }
    }
}
