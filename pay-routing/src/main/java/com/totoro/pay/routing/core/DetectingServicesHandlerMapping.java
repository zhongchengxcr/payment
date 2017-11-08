package com.totoro.pay.routing.core;

import com.totoro.pay.channel.channel.ChannelPayProcess;
import com.totoro.pay.routing.annotation.AnnotationTest;
import com.totoro.pay.channel.annotation.ChannelMapping;
import com.totoro.pay.routing.exception.HandlerRegistException;
import com.totoro.pay.routing.util.AopTargetUtils;
import com.totoro.pay.routing.util.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
public class DetectingServicesHandlerMapping implements HandlerMapping, InitializingBean {

    @Autowired
    private Map<String, ChannelPayProcess> channels;

    @Autowired
    private MappingRegistry mappingRegistry;


    private Map<String, ChannelPayProcess> registFailedChannels = new ConcurrentHashMap<>();


    @Override
    public Object getHandler(String services) {
        return mappingRegistry.getMapingByService(services);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("================================================");
        initHandlerMethods();
    }


    public static void main(String[] args) {

//        Set<Method> methods = MethodIntrospector.selectMethods(AnnotationTest.class, (ReflectionUtils.MethodFilter) method -> {
//
//            Annotation annotation = AnnotationUtils.findAnnotation(method, ChannelMapping.class);
//
//            if (annotation != null) {
//                return true;
//            }
//            return false;
//
//        });
//
//
//        methods.stream().map(method -> method.getName()).forEach((System.out::println));

        System.out.println(AnnotatedElementUtils.hasAnnotation(AnnotationTest.class, ChannelMapping.class));

    }

    /**
     * 初始化
     */
    protected void initHandlerMethods() {
        this.detectHandlers();
        for (Map.Entry<String, ChannelPayProcess> entry : channels.entrySet()) {
            try {
                detectHandlerMethods(entry);
            } catch (HandlerRegistException hex) {
                registFailedChannels.put(entry.getKey(), entry.getValue());
                continue;
            }
        }
    }

    public void detectHandlers() {
        channels.entrySet().stream().filter((entry) -> {
            ChannelPayProcess channelProcess = entry.getValue();
            try {
                Object channel = AopTargetUtils.getTarget(channelProcess);
                Class channelType = channel.getClass();
                return isChannelHandler(channelType);
            } catch (Exception e) {

            }
            return false;
        });
    }

    protected void detectHandlerMethods(final Map.Entry<String, ChannelPayProcess> entry) {
        try {
            Object channel = AopTargetUtils.getTarget(entry.getValue());
            Set<Method> methods = MethodIntrospector.selectMethods(channel.getClass(), (ReflectionUtils.MethodFilter) method -> {
                Annotation annotation = AnnotationUtils.findAnnotation(method, ChannelMapping.class);
                if (annotation != null) {
                    return true;
                }
                return false;
            });

            ChannelMapping clssChannelMapping = AnnotationUtils.findAnnotation(channel.getClass(), ChannelMapping.class);

            String routingServicePrefix = StringUtils.ignoreEmpty(clssChannelMapping.value());


            if (!StringUtils.endsWithIgnoreCase(routingServicePrefix, ".")) {
                routingServicePrefix += ".";
            }

            for (Method method : methods) {
                ChannelMapping methodChannelMapping = AnnotationUtils.findAnnotation(method, ChannelMapping.class);
                String routingServiceSuffix = StringUtils.ignoreEmpty(methodChannelMapping.value());
                if (StringUtils.startsWithIgnoreCase(routingServiceSuffix, ".")) {
                    routingServiceSuffix = StringUtils.tails(routingServiceSuffix);
                }

                String service = routingServicePrefix + routingServiceSuffix;
                registMapping(service, channel, method);
            }

        } catch (Exception e) {
            throw new HandlerRegistException("Handler regist failed :" + entry.getKey());
        }
    }


    private void registMapping(String service, Object channel, Method method) {
        HandlerMethodInfo handlerMethodInfo = new HandlerMethodInfo();
        handlerMethodInfo.setMethod(method).setChannel(channel);
        mappingRegistry.register(service, handlerMethodInfo);
    }

    protected boolean isChannelHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, ChannelMapping.class);
    }
}
