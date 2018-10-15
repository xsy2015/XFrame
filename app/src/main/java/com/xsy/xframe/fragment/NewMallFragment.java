package com.xsy.xframe.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.xsy.base.ui.BaseFragment;
import com.xsy.xframe.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2016/10/10
 */
public class NewMallFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_mall;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        mTopBar.setTitle("mall");
        mTopBar.setTitleGravity(Gravity.CENTER);
    }
}
