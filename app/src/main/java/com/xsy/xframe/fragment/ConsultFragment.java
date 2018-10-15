package com.xsy.xframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.xsy.base.ui.BaseFragment;
import com.xsy.xframe.R;
import com.xsy.xframe.adapter.TizhiListAdpter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2016/10/10
 */
public class ConsultFragment extends BaseFragment {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    public static ConsultFragment newInstance() {

        Bundle args = new Bundle();

        ConsultFragment fragment = new ConsultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_consult;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        mTopBar.setTitle("group");
        mTopBar.setTitleGravity(Gravity.CENTER);
        initView();
    }

    private void initView() {
        TizhiListAdpter adpter = new TizhiListAdpter(getActivity());
        lv.setAdapter(adpter);
        adpter.notifyDataSetChanged();
    }
}
