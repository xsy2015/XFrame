package com.xsy.xframe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;
import com.xsy.base.R;
import com.xsy.base.R2;
import com.xsy.base.ui.BaseFragmentActivity;
import com.xsy.base.ui.view.ProgressWebView;
import com.xsy.xframe.view.sonicwebview.HostSonicRuntime;
import com.xsy.xframe.view.sonicwebview.SonicJavaScriptInterface;
import com.xsy.xframe.view.sonicwebview.SonicRuntimeImpl;
import com.xsy.xframe.view.sonicwebview.SonicSessionClientImpl;

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
    private SonicSession sonicSession;
    private Intent intent;
    private SonicSessionClientImpl sonicSessionClient = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_display_h5_page;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {

        intent = getIntent();
        initView();

        // step 1: Initialize sonic engine if necessary
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(this), new SonicConfig.Builder().build());
        }

        // step 2: Create SonicSession
        sonicSession = SonicEngine.getInstance().createSession(url,  new SonicSessionConfig.Builder().build());
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
        } else {
            // this only happen when a same sonic session is already running,
            // u can comment following codes to feedback as a default mode.
            throw new UnknownError("create session fail!");
        }


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

        mWebview.removeJavascriptInterface("searchBoxJavaBridge_");
        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis());
        mWebview.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient, intent), "sonic");

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
                if (sonicSession != null) {
                    sonicSession.getSessionClient().pageFinish(url);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //LogUtil.e(TAG, "onReceivedError");
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (sonicSession != null) {
                    //step 6: Call sessionClient.requestResource when host allow the application
                    // to return the local data .
                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
                }
                return null;
            }
        });
        // step 5: webview is ready now, just tell session client to bind
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(mWebview);
            sonicSessionClient.clientReady();
        } else { // default mode
            mWebview.loadUrl(url);
        }
    }

    @Override
    protected void onDestroy() {
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
        }
        super.onDestroy();
    }
}
