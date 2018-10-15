package com.xsy.xframe.event;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2017/9/30
 */

public class ChangeTopViewEvent {
    private int offsetY;
    private int y;

    public ChangeTopViewEvent(int offsetY,int y) {
        this.offsetY = offsetY;
        this.y=y;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getY() {
        return y;
    }
}
