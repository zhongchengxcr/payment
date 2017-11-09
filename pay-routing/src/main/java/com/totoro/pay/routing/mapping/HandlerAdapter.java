package com.totoro.pay.routing.mapping;

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
public interface HandlerAdapter {

    /**
     * Given a handler instance, return whether or not this {@code HandlerAdapter}
     * can support it. Typical HandlerAdapters will base the decision on the handler
     * type. HandlerAdapters will usually only support one handler type each.
     * <p>A typical implementation:
     * <p>{@code
     * return (handler instanceof MyHandler);
     * }
     *
     * @param handler handler object to check
     * @return whether or not this object can use the given handler
     */
    boolean supports(Object handler);

    /**
     * Use the given handler to handle this request.
     * The workflow that is required may vary widely.
     *
     * @param args    current routing request
     * @param handler handler to use. This object must have previously been passed
     *                to the {@code supports} method of this interface, which must have
     *                returned {@code true}.
     * @return channel api reponse
     * model data, or {@code null} if the request has been handled directly
     */
    Object handle(Object handler, Object... args);

}
