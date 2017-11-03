package com.totoro.pay.api.requert;

/**
 * 说明 . <br>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2017/11/03 下午10:36
 * <p>
 * Company: xxx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public class NoticeBodyWarpper<T> {

    private String type;

    private T t;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
