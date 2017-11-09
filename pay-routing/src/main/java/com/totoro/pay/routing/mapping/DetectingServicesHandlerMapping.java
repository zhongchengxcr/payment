package com.totoro.pay.routing.mapping;

import com.google.common.base.Preconditions;
import com.totoro.pay.channel.annotation.ChannelMapping;
import com.totoro.pay.channel.channel.ChannelPayProcess;
import com.totoro.pay.routing.exception.HandlerRegistryException;
import com.totoro.pay.routing.util.AopTargetUtils;
import com.totoro.pay.routing.util.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    private MappingRegistry mappingRegistry = new MappingRegistry();


    private Map<String, ChannelPayProcess> registryFailedMapping = new ConcurrentHashMap<>();


    @Override
    public Object getHandler(String services) {
        return mappingRegistry.getMappingByService(services);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initHandlerMethods();
    }


    /**
     * 初始化
     */
    private void initHandlerMethods() {
        this.detectHandlers();
        for (Map.Entry<String, ChannelPayProcess> entry : channels.entrySet()) {
            try {
                detectHandlerMethods(entry);
            } catch (HandlerRegistryException hex) {
                registryFailedMapping.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 过滤每个实现了 ChannelPayProcess 的 bean,找出 "渠道"
     */
    private void detectHandlers() {


        Set<String> set = channels.entrySet().stream().filter((entry) -> {

            ChannelPayProcess channelProcess = entry.getValue();
            try {
                Object channel = AopTargetUtils.getTarget(channelProcess);
                Class channelType = channel.getClass();
                return !isChannelHandler(channelType);
            } catch (Exception ignored) {
                //忽略直接返回false
            }
            return false;
        }).map((entry) -> entry.getKey()).collect(Collectors.toSet());


        set.stream().forEach((key) -> channels.remove(key));

    }


    /**
     * 过滤每个方法
     *
     * @param entry channel
     */
    private void detectHandlerMethods(final Map.Entry<String, ChannelPayProcess> entry) {
        try {
            Object channel = AopTargetUtils.getTarget(entry.getValue());
            Set<Method> methods = MethodIntrospector.selectMethods(channel.getClass(), (ReflectionUtils.MethodFilter) method -> {
                Annotation annotation = AnnotationUtils.findAnnotation(method, ChannelMapping.class);
                return annotation != null;
            });

            ChannelMapping classChannelMapping = AnnotationUtils.findAnnotation(channel.getClass(), ChannelMapping.class);

            String routingServicePrefix = StringUtils.ignoreEmpty(classChannelMapping.value());


            if (!StringUtils.isEmpty(routingServicePrefix) && !StringUtils.endsWithIgnoreCase(routingServicePrefix, ".")) {
                routingServicePrefix += ".";
            }

            for (Method method : methods) {
                ChannelMapping methodChannelMapping = AnnotationUtils.findAnnotation(method, ChannelMapping.class);
                String routingServiceSuffix = StringUtils.ignoreEmpty(methodChannelMapping.value());
                if (StringUtils.startsWithIgnoreCase(routingServiceSuffix, ".")) {
                    routingServiceSuffix = StringUtils.tails(routingServiceSuffix);
                }

                String service = routingServicePrefix + routingServiceSuffix;
                registerMapping(service, channel, method);
            }

        } catch (Exception e) {
            throw new HandlerRegistryException("Handler register failed :" + entry.getKey());
        }
    }


    private void registerMapping(String service, Object channel, Method method) {
        MethodHandlerDefinition handlerMethodInfo = new MethodHandlerDefinition();
        handlerMethodInfo.setMethod(method).setChannel(channel).setClzz(channel.getClass());
        mappingRegistry.register(service, handlerMethodInfo);
    }

    private boolean isChannelHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, ChannelMapping.class);
    }


    /**
     * 注册Handler
     */
    class MappingRegistry {

        private Map<String, MethodHandlerDefinition> mappingLookup = new ConcurrentHashMap<>(16);

        void register(String service, MethodHandlerDefinition handlerMethodInfo) {
            Preconditions.checkArgument(!mappingLookup.containsKey(service), "Duplicate service : %s ", service);
            Preconditions.checkNotNull(handlerMethodInfo, "HandlerMethodInfo is null");
            Preconditions.checkNotNull(handlerMethodInfo.getChannel(), "HandlerMethodInfo channel is null");
            Preconditions.checkNotNull(handlerMethodInfo.getClzz(), "HandlerMethodInfo class type is null");
            mappingLookup.put(service, handlerMethodInfo);
        }


        private Map<String, MethodHandlerDefinition> getMappings() {
            return this.mappingLookup;
        }

        private MethodHandlerDefinition getMappingByService(String service) {
            return mappingLookup.get(service);
        }


    }
}
