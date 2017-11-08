package com.totoro.pay.routing.mapping;

import com.google.common.base.Preconditions;
import com.totoro.pay.routing.exception.HandlerAdapterInvokeIgArgsException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
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
@Component
public class MethodHandlerAdapter implements HandlerAdapter {

    private static final String REQ_NPE_ERROR_MESSAGE = "Method param is null!";

    private static final String HANDLER_NPE_ERROR_MESSAGE = "Hanler defintion is null";

    /**
     * 这个方法暂时用处不大
     *
     * @param handler handler object to check
     * @return
     */
    @Override
    public boolean supports(Object handler) {
        return MethodHandlerDefinition.class.isAssignableFrom(handler.getClass());
    }

    @Override
    public Object handle(Object handler, Object... args) {

        MethodHandlerDefinition methodHandlerDefinition = (MethodHandlerDefinition) handler;

        try {
            checkRequest(methodHandlerDefinition, args);
        } catch (HandlerAdapterInvokeIgArgsException e) {
            args = null;
        }


        Method method = methodHandlerDefinition.getMethod();
        Object channel = methodHandlerDefinition.getChannel();
        Object returnValue = doInvoke(channel, method, args);
        //classes.
        return returnValue;
    }

    private Object doInvoke(Object channel, Method method, Object[] args) {
        Object returnValue = null;
        ReflectionUtils.makeAccessible(method);
        try {
            returnValue = method.invoke(channel, args);
        } catch (IllegalAccessException e) {
            //TODO
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            //TODO
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            //TODO
            e.printStackTrace();
        }
        return returnValue;
    }

    private void checkRequest(MethodHandlerDefinition handler, Object[] request) {
        Preconditions.checkNotNull(request, REQ_NPE_ERROR_MESSAGE);
        Preconditions.checkNotNull(handler, HANDLER_NPE_ERROR_MESSAGE);
        Class[] classes = handler.getMethod().getParameterTypes();

        if (classes.length == request.length) {
            for (int i = 0; i < classes.length; i++) {
                if (!classes[i].isAssignableFrom(request[i].getClass())) {
                    //参数类型不匹配
                    throw new RuntimeException("iglle");
                }
            }
        } else {
            if (classes == null || classes.length == 0) {
                throw new HandlerAdapterInvokeIgArgsException("param is  not match");
            } else {
                throw new RuntimeException("iglle");
            }
        }
    }
}
