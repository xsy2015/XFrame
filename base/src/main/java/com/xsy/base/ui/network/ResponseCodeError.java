package com.xsy.base.ui.network;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2018/9/6
 */

public class ResponseCodeError extends RuntimeException{
    public ResponseCodeError(String detailMessage) {
        super(detailMessage);
    }
}
