package com.xsy.xframe.view.sonicwebview;

import android.os.Bundle;

import com.tencent.sonic.sdk.SonicSessionClient;
import com.xsy.base.ui.view.ProgressWebView;

import java.util.HashMap;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2018/10/23
 */

public class SonicSessionClientImpl extends SonicSessionClient {
    private ProgressWebView mWebView;

    public ProgressWebView getmWebView() {
        return mWebView;
    }

    public void setmWebView(ProgressWebView mWebView) {
        this.mWebView = mWebView;
    }

    public void bindWebView(ProgressWebView webView) {
        this.mWebView = webView;
    }

    @Override
    public void loadUrl(String url, Bundle extraData) {
        mWebView.loadUrl(url);
    }

    @Override
    public void loadDataWithBaseUrl(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
       mWebView.loadDataWithBaseURL(baseUrl,data,mimeType,encoding,historyUrl);
    }

    @Override
    public void loadDataWithBaseUrlAndHeader(String baseUrl, String data, String mimeType, String encoding, String historyUrl, HashMap<String, String> headers) {
        mWebView.loadUrl(baseUrl,headers);
    }
}
