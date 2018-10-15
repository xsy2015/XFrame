package com.xsy.xframe.fragment;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.xsy.base.ui.BaseFragment;
import com.xsy.xframe.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2018/5/7
 */

public class OtherFragment extends BaseFragment {
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    private List<Fragment> fragmentList = new ArrayList<>();

    public static final String External_Storage_Directory_Root = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/kmcloud";
    // 外存储卡的文件目录
    public static final String External_Storage_Directory_FilePath = External_Storage_Directory_Root
            + "/files/";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        PermissionGen.needPermission(OtherFragment.this, 100,
                new String[] {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET
                }
        );
        createDir();
        BasicMassageFragment basicMassageFragment = new BasicMassageFragment();
        SpecialMassageFragment specialMassageFragment = new SpecialMassageFragment();
        fragmentList.add(basicMassageFragment);
        fragmentList.add(specialMassageFragment);
        mViewPager.setAdapter(new MassagePagerAdapter(getChildFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(0);
    }
    private class MassagePagerAdapter extends FragmentPagerAdapter {
        public MassagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // @PermissionSuccess(requestCode = 100)
    private void createDir() {
        File file = new File(External_Storage_Directory_FilePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        System.out.println("----------"+file.getAbsolutePath());
        System.out.println("----------"+External_Storage_Directory_FilePath);
        file = new File(External_Storage_Directory_FilePath+getPhotoFileName());
        if (file.exists()) {
            file.delete();
        }
        System.out.println("----------"+file.getPath());
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        Toast.makeText(getActivity(), "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }

    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss",
                Locale.getDefault());
        return dateFormat.format(date) + ".jpg";
    }

}
