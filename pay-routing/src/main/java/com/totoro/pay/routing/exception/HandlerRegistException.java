package com.totoro.pay.routing.exception;

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
public class HandlerRegistException extends RuntimeException {

    public HandlerRegistException(String message) {
        super(message);
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
