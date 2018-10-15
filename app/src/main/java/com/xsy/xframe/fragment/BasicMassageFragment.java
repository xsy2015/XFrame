package com.xsy.xframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xsy.xframe.R;
import com.xsy.xframe.event.ChangeTopViewEvent;
import com.xsy.xframe.view.ObservableScrollView;

import org.greenrobot.eventbus.EventBus;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2017/2/20
 */
public class BasicMassageFragment extends Fragment implements ObservableScrollView.ScrollViewListener {

    private ObservableScrollView mScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_basic_massage, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {

        TextView tv1 = (TextView) rootView.findViewById(R.id.tv1);
        mScrollView = (ObservableScrollView) rootView.findViewById(R.id.sv);
        mScrollView.setScrollViewListener(this);

        tv1.setText("自定义ViewGroup一般是利用现有的组件根据特定的布局方式来组成新的组件，大多继承自ViewGroup或各种Layout，包含有子View。例如：应用底部导航条中的条目，一般都是上面图标(ImageView)，下面文字(TextView)，" +
                "那么这两个就可以用自定义ViewGroup组合成为一个Veiw，提供两个属性分别用来设置文字和图片，使用起来会更加方便。2.自定义View\n" +
                "\n" +
                "在没有现成的View，需要自己实现的时候，就使用自定义View，一般继承自View，SurfaceView或其他的View，不包含子View。例如：制作一个支持自动加载网络图片的ImageView，制作图表等。\n" +
                "PS： 自定义View在大多数情况下都有替代方案，利用图片或者组合动画来实现，但是使用后者可能会面临内存耗费过大，制作麻烦更诸多问题。.几个重要的函数\n" +
                "1.构造函数\n" +
                "\n" +
                "构造函数是View的入口，可以用于初始化一些的内容，和获取自定义属性。\n" +
                "\n" +
                "View的构造函数有四种重载分别如下:可以看出，关于View构造函数的参数有多有少，先排除几个不常用的，留下常用的再研究。\n" +
                "\n" +
                "有四个参数的构造函数在API21的时候才添加上，暂不考虑。\n" +
                "\n" +
                "有三个参数的构造函数中第三个参数是默认的Style，这里的默认的Style是指它在当前Application或Activity所用的Theme中的默认Style，且只有在明确调用的时候才会生效，以系统中的ImageButton为例说明：Define events:\n" +
                "\n" +
                "public static class MessageEvent { /* Additional fields if needed */ }Prepare subscribers: Declare and annotate your subscribing method, optionally specify a thread mode:\n" +
                "\n" +
                "@Subscribe(threadMode = ThreadMode.MAIN)  \n" +
                "public void onMessageEvent(MessageEvent event) {/* Do something */};Register and unregister your subscriber. For example on Android, activities and fragments should usually register according to their life cycle:\n" +
                "\n" +
                " @Override\n" +
                " public void onStart() {\n" +
                "     super.onStart();\n" +
                "     EventBus.getDefault().register(this);\n" +
                " }\n" +
                "\n" +
                " @Override\n" +
                " public void onStop() {\n" +
                "     super.onStop();\n" +
                "     EventBus.getDefault().unregister(this);\n" +
                " }Post events:\n" +
                "\n" +
                " EventBus.getDefault().post(new MessageEvent());");
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        Log.i("ScrollView","x="+x+" oldx="+oldx+" y="+y+" oldy="+oldy+" y-oldy="+(y-oldy));
        EventBus.getDefault().post(new ChangeTopViewEvent(y-oldy,y));
    }
}
