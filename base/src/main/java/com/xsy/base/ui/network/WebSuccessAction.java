package com.xsy.base.ui.network;

import rx.functions.Action1;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2018/9/6
 */

public abstract class WebSuccessAction<T extends JsonDataResponse> implements Action1<T> {

    @Override
    public void call(T response) {
        int rc = response.getRc();
        if (rc != 0) {
            throw new ResponseCodeError(response.getMsg());
        }
        onSuccess(response);
    }

    public abstract void onSuccess(T extendedResponse);
}
