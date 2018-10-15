package com.xsy.base.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.xsy.base.R;
import com.xsy.base.R2;
import com.xsy.base.ui.BaseFragmentActivity;
import com.xsy.base.ui.view.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2016/11/18
 */
public class DisplayH5PageActivity extends BaseFragmentActivity {
    private static final String TAG = "HealthInfoWebActivity";
    @BindView(R2.id.mWebView)
    ProgressWebView mWebview;
    @BindView(R2.id.topbar)
    QMUITopBarLayout mTopBar;
    private String url;
    private String titleName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_display_h5_page;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        initView();
        initWebView();
    }

    private void initView() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        titleName = intent.getStringExtra("titleName");
        mTopBar.setTitle(titleName);
        mTopBar.setTitleGravity(Gravity.CENTER);
    }

    private void initWebView() {
        mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//不加上，会显示白边
        WebSettings webSettings = mWebview.getSettings();
        //百分比大小
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        //LogUtil.e(TAG, "mDensity--->" + mDensity);
        //酷派Y60C1 240
        if (mDensity == 240) {
            //百分比大小
            mWebview.setInitialScale(150);
        } else if (mDensity == 160) {
            //百分比大小
            mWebview.setInitialScale(200);
        } else if (mDensity == 640) {//三星手机
            //百分比大小
            mWebview.setInitialScale(80);
            webSettings.setTextZoom(80);
        } else if (mDensity == 480) {//huawei knt-al10，努比亚
            mWebview.setInitialScale(80);//130
            webSettings.setTextZoom(80);
        } else if (mDensity == 320) {//xiaolajiao
            mWebview.setInitialScale(60);//40 ,162
            webSettings.setTextZoom(130);
        } else {
            mWebview.setInitialScale(100);
        }

        // User settings
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//NARROW_COLUMNS

//        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(false); // 设置显示缩放按钮
        webSettings.setSupportZoom(false); // 支持缩放
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultFontSize(16);

        //加载网页
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //LogUtil.d(TAG, "onPageFinished");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //LogUtil.e(TAG, "onReceivedError");
            }
        });
        mWebview.loadUrl(url);
    }
}
