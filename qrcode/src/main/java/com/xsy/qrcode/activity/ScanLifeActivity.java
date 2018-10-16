package com.xsy.qrcode.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.xsy.base.R2;
import com.xsy.base.ui.BaseFragmentActivity;
import com.xsy.base.ui.utils.ToastUtils;
import com.xsy.qrcode.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2017/10/10
 */

public class ScanLifeActivity extends BaseFragmentActivity {

    private CaptureFragment captureFragment;
    private FrameLayout fl_my_container;
    private QMUITopBarLayout mTopBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_life;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.fragment_scan_life);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

        initView();
    }

    private void initView() {

        fl_my_container = findViewById(R.id.fl_my_container);
        mTopBar = findViewById(R.id.topbar);

        mTopBar.setTitle("二维码/条码");
        mTopBar.setTitleGravity(Gravity.CENTER);
    }
    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            ToastUtils.showShort("扫描成功 result="+result);
            finish();
        }

        @Override
        public void onAnalyzeFailed() {
            ToastUtils.showShort("扫描二维码失败");
        }
    };


}
