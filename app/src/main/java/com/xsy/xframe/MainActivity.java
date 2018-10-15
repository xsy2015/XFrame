package com.xsy.xframe;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xsy.base.ui.BaseFragmentActivity;
import com.xsy.xframe.fragment.CenterFragment;
import com.xsy.xframe.fragment.ConsultFragment;
import com.xsy.xframe.fragment.HealthGroupFragment;
import com.xsy.xframe.fragment.HomeFragment;
import com.xsy.xframe.fragment.NewMallFragment;
import com.xsy.xframe.view.MartianTabWidget;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity implements MartianTabWidget.OnTabSelectionChanged, ViewPager.OnPageChangeListener {

    private MartianTabWidget martianTabWidget;
    private ViewPager viewPager;

    private final int FRAGMENT_SIZE = 5;

    /* 底部栏文字 */
    private String tab_text_strs[];

    /* 底部栏图片id */
    private int tab_ic_ids[] = {R.drawable.ic_tab_home_selector, R.drawable.ic_tab_group_selector,
            R.drawable.ic_tab_consult_selector, R.drawable.ic_tab_mall_selector, R.drawable.ic_tab_center_selector};


    private List<Fragment> list = new ArrayList<>();
    private FragmentManager fragmentManager;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra("", "");
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        tab_text_strs = new String[FRAGMENT_SIZE];
        tab_text_strs[0] = getString(R.string.home);
        tab_text_strs[1] = getString(R.string.health_group);
        tab_text_strs[2] = getString(R.string.consult);
        tab_text_strs[3] = getString(R.string.mall);
        tab_text_strs[4] = getString(R.string.personal_center);

        fragmentManager = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        martianTabWidget = (MartianTabWidget) findViewById(R.id.martianTabWidget);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        martianTabWidget.setTabSelectionListener(this);
        for (int i = 0; i < tab_text_strs.length; i++) {
            martianTabWidget.addView(getView(i));
        }

        martianTabWidget.setCurrentTab(0);

        //除去分割线
//        fragmentTabHost.getTabWidget().setDividerDrawable(null);

        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(tab_text_strs.length);

        HomeFragment fragment1 = new HomeFragment();
        HealthGroupFragment fragment2 = new HealthGroupFragment();
        ConsultFragment fragment3 = new ConsultFragment();
        NewMallFragment fragment4 = new NewMallFragment();
        CenterFragment fragment5 = new CenterFragment();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        list.add(fragment5);
        viewPager.setAdapter(new HomeViewPagerAdapter(fragmentManager));
    }

    private class HomeViewPagerAdapter extends FragmentPagerAdapter {
        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return tab_text_strs.length;
        }
    }

    @Override
    public void onTabSelectionChanged(int tabIndex, boolean clicked) {
        viewPager.setCurrentItem(tabIndex);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        int oldFocusability = martianTabWidget.getDescendantFocusability();
        martianTabWidget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        martianTabWidget.setCurrentTab(position);
        martianTabWidget.setDescendantFocusability(oldFocusability);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * @param i 第几个tab
     * @Description描述: 获取底部tab的view
     */
    private View getView(int i) {
        //取得布局实例
        View view = View.inflate(MainActivity.this, R.layout.tab_content_home, null);

        //取得布局对象
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_tabContent_home);
        TextView textView = (TextView) view.findViewById(R.id.tv_tabContent_home);

        //设置图标
        imageView.setImageResource(tab_ic_ids[i]);
        //设置文字
        textView.setText(tab_text_strs[i]);

        return view;
    }
}
