package com.totoro.pay.routing.mapping;

import com.totoro.pay.routing.exception.HandlerAdapterInvokeIllegalArgumentException;
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


    private static final String HANDLER_NPE_ERROR_MESSAGE = "Hanler defintion is null";

    private static final String PARAM_NOT_MATCH = "Parameter mismatch at index : %s ";

    //是否开启严格检查模式 , 默认false
    private boolean strictInspectionStrategy = true;


    /**
     * 这个方法暂时用处不大
     *
     * @param handler handler object to check
     * @return 是否支持
     */
    @Override
    public boolean supports(Object handler) {
        return MethodHandlerDefinition.class.isAssignableFrom(handler.getClass());
    }

    @Override
    public Object handle(Object handler, Object... args) {

        MethodHandlerDefinition methodHandlerDefinition = (MethodHandlerDefinition) handler;


        if (strictInspectionStrategy) {
            args = strictCheckAndAdapterRequest(methodHandlerDefinition, args);
        } else {
            args = checkAndAdapterRequest(methodHandlerDefinition, args);
        }

        Method method = methodHandlerDefinition.getMethod();
        Object channel = methodHandlerDefinition.getChannel();
        Object returnValue = doInvoke(channel, method, args);

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


    /**
     * 检查策略:兼容模式
     * <p>
     * 1,如果目标方法没有参数,则直接忽略传入的参数
     * 2,如果目标方法有参数,但没有传入参数,则默认传入空参数
     * 3,如果传入参数个数大于目标方法参数个数,则顺序匹配(不匹配报错),忽略超出长处的
     * 4,如果传入参数个数小于目标方法参数个数,为则顺序匹配(不匹配报错),未传入部分按照 null 补全
     *
     * @param handler
     * @param request
     * @return
     * @throws HandlerAdapterInvokeIllegalArgumentException
     */
    private Object[] checkAndAdapterRequest(MethodHandlerDefinition handler, Object[] request) throws HandlerAdapterInvokeIllegalArgumentException {

        //空校验
        if (handler == null) {
            throw new HandlerAdapterInvokeIllegalArgumentException(HANDLER_NPE_ERROR_MESSAGE);
        }

        Class[] classes = handler.getMethod().getParameterTypes();

        //方法没有参数的情况
        if (classes == null || classes.length == 0) {
            //直接忽略传入的参数
            return null;
        } else {

            int paramNum = classes.length;

            Object[] param = new Object[paramNum];

            // 方法有参数,但是没有传入,直接返回 param
            if (request == null || request.length == 0) {
                return param;
            }

            //遍历
            for (int i = 0; i < paramNum && i < request.length; i++) {

                Class type = classes[i];

                if (request[i].getClass().isAssignableFrom(type)) {
                    param[i] = request[i];
                } else {
                    throw new HandlerAdapterInvokeIllegalArgumentException(String.format(PARAM_NOT_MATCH, i));
                }
            }
            return param;
        }
    }


    /**
     * 检查策略:精确模式
     * <p>
     * 传入的参数必须和目标方法参数类型完全匹配,null除外
     *
     * @param handler
     * @param request
     * @return
     * @throws HandlerAdapterInvokeIllegalArgumentException
     */
    private Object[] strictCheckAndAdapterRequest(MethodHandlerDefinition handler, Object[] request) throws HandlerAdapterInvokeIllegalArgumentException {
        //空校验
        if (handler == null) {
            throw new HandlerAdapterInvokeIllegalArgumentException(HANDLER_NPE_ERROR_MESSAGE);
        }

        Class[] classes = handler.getMethod().getParameterTypes();

        if (classes == null || classes.length == 0) {
            if (request != null && request.length != 0) {
                throw new HandlerAdapterInvokeIllegalArgumentException(String.format(PARAM_NOT_MATCH, 0));
            } else {
                return null;
            }
        } else {
            if (request == null || request.length == 0) {
                throw new HandlerAdapterInvokeIllegalArgumentException(String.format(PARAM_NOT_MATCH, 0));
            }
            if (classes.length != request.length) {
                throw new HandlerAdapterInvokeIllegalArgumentException(String.format(PARAM_NOT_MATCH, "*"));
            } else {
                Object[] param = new Object[classes.length];
                for (int i = 0; i < classes.length; i++) {
                    if (request[i] != null) {
                        if (!request[i].getClass().isAssignableFrom(classes[i])) {
                            throw new HandlerAdapterInvokeIllegalArgumentException(String.format(PARAM_NOT_MATCH, i));
                        } else {
                            param[i] = request[i];
                        }
                    }
                }
                return param;
            }
        }
    }

    public boolean isStrictInspectionStrategy() {
        return strictInspectionStrategy;
    }

    public MethodHandlerAdapter setStrictInspectionStrategy(boolean strictInspectionStrategy) {
        this.strictInspectionStrategy = strictInspectionStrategy;
        return this;
    }
}
