package com.totoro.pay.routing.mapping;

import com.google.common.base.Preconditions;
import com.totoro.pay.channel.annotation.ChannelMapping;
import com.totoro.pay.channel.channel.ChannelPayProcess;
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

    private MappingRegistry mappingRegistry = new MappingRegistry();


    private Map<String, ChannelPayProcess> registFailedChannels = new ConcurrentHashMap<>();


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

    /**
     * 过滤每个实现了 ChannelPayProcess 的 bean,找出 "渠道"
     */
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


    /**
     * 过滤每个方法
     *
     * @param entry
     */
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

            ChannelMapping classChannelMapping = AnnotationUtils.findAnnotation(channel.getClass(), ChannelMapping.class);

            String routingServicePrefix = StringUtils.ignoreEmpty(classChannelMapping.value());


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
                registerMapping(service, channel, method);
            }

        } catch (Exception e) {
            throw new HandlerRegistException("Handler register failed :" + entry.getKey());
        }
    }


    private void registerMapping(String service, Object channel, Method method) {
        MethodHandlerDefinition handlerMethodInfo = new MethodHandlerDefinition();
        handlerMethodInfo.setMethod(method).setChannel(channel);
        mappingRegistry.register(service, handlerMethodInfo);
    }

    protected boolean isChannelHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, ChannelMapping.class);
    }


    /**
     * 注册Handler
     */
    class MappingRegistry {

        private Map<String, MethodHandlerDefinition> mappingLookup = new ConcurrentHashMap<>(16);

        public void register(String service, MethodHandlerDefinition handlerMethodInfo) {
            Preconditions.checkArgument(!mappingLookup.containsKey(service), "Duplicate service : %s ", service);
            Preconditions.checkNotNull(handlerMethodInfo, "HandlerMethodInfo is null");
            //TODO 检查
            mappingLookup.put(service, handlerMethodInfo);
        }


        public Map<String, MethodHandlerDefinition> getMappings() {
            return this.mappingLookup;
        }

        public MethodHandlerDefinition getMappingByService(String service) {
            return mappingLookup.get(service);
        }


    }
}
