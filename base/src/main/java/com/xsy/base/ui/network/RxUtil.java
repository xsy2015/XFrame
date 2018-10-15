package com.xsy.base.ui.network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2018/9/6
 */

public class RxUtil {

    public static <T> Observable.Transformer<T, T> normalSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> source) {
                return source.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
