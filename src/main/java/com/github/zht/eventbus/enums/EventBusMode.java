package com.github.zht.eventbus.enums;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/15
 */
public enum EventBusMode {

    SYNC(1),
    ASYNC(2);

    private int mode;

    public int getMode() {
        return mode;
    }

    EventBusMode(int mode) {
        this.mode = mode;
    }
}
