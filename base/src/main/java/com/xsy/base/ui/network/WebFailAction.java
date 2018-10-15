package com.xsy.base.ui.network;

import android.widget.Toast;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2018/9/6
 */

public class WebFailAction implements Action1<Throwable> {

    @Override
    public void call(Throwable throwable) {
        String errorMsg = "";
        if (throwable instanceof IOException) {
            errorMsg = "Please check your network status";
        } else if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            // such as: "server internal error".
            errorMsg = String.valueOf(httpException.response());
        } else {
            errorMsg = "unknown error";
        }
        //Toast.makeToast(...);
    }

}
