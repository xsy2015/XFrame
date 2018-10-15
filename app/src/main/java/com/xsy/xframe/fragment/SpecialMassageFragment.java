package com.xsy.xframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xsy.xframe.R;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2017/2/20
 */
public class SpecialMassageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_special_massage, null);
        return rootView;
    }
}
