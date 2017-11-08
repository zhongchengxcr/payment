package com.totoro.pay.routing.core;

import java.lang.reflect.Method;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c)
 * <p>
 * Company: xx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public class HandlerMethodInfo {

    private String name;

    private Class clzz;

    private Object channel;

    private Method method;


    public String getName() {
        return name;
    }

    public HandlerMethodInfo setName(String name) {
        this.name = name;
        return this;
    }

    public Class getClzz() {
        return clzz;
    }

    public HandlerMethodInfo setClzz(Class clzz) {
        this.clzz = clzz;
        return this;
    }

    public Object getChannel() {
        return channel;
    }

    public HandlerMethodInfo setChannel(Object channel) {
        this.channel = channel;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public HandlerMethodInfo setMethod(Method method) {
        this.method = method;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HandlerMethodInfo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", clzz=").append(clzz);
        sb.append(", channel=").append(channel);
        sb.append(", method=").append(method);
        sb.append('}');
        return sb.toString();
    }
}
