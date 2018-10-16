package com.xsy.base.ui.utils;

/**
 * Created by Administrator on 2016-8-6.
 */

import android.app.Application;
import android.widget.Toast;
import com.xsy.base.ui.BaseApplication;

/**
 * Toast统一管理类
 */
public class ToastUtils {

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;
    private static long oneTime = 0;
    private static long twoTime = 0;
    private static String oldMsg;
    private static Toast mToast;

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(String message) {
        show(message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
        show(message, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(String message) {
        show(message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        show(message, Toast.LENGTH_LONG);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(String message, int duration) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(BaseApplication.instance.getApplicationContext(), message, duration);
                mToast.show();
                oneTime = System.currentTimeMillis();
            } else {
                twoTime = System.currentTimeMillis();
                if (message.equals(oldMsg)) {
                    if (twoTime - oneTime > duration) {
                        mToast.show();
                    }
                } else {
                    mToast.setText(message);
                    oldMsg = message;
                    mToast.show();
                }
            }
            oneTime = twoTime;
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(int message, int duration) {
        show( BaseApplication.instance.getApplicationContext().getString(message), duration);
    }
}
