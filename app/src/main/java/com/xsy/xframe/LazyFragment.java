package com.xsy.xframe;

import android.support.v4.app.Fragment;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2017/6/22
 */

public abstract class LazyFragment extends Fragment {
    protected boolean isVisible;
    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    protected void onVisible(){
        lazyLoad();
    }
    protected abstract void lazyLoad();
    protected void onInvisible(){}
}
