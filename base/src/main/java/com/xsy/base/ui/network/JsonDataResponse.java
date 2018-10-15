package com.xsy.base.ui.network;

import com.google.gson.annotations.SerializedName;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2018/9/6
 */

public class JsonDataResponse<T> {
    @SerializedName("ResultCode")
    private int rc;

    @SerializedName("ResultMessage")
    private String msg;

    @SerializedName("Data")
    T data;

    public int getRc() { return rc; }

    public String getMsg() {
        return msg;
    }

    public T getData() { return data; }
}
