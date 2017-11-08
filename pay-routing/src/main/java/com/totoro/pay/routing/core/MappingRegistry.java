package com.totoro.pay.routing.core;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

import java.util.Map;
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
public class MappingRegistry {

    private Map<String, HandlerMethodInfo> mappingLookup = new ConcurrentHashMap<>(16);

    public void register(String service, HandlerMethodInfo handlerMethodInfo) {
        Preconditions.checkArgument(!mappingLookup.containsKey(service), "Duplicate service : %s ", service);
        Preconditions.checkNotNull(handlerMethodInfo, "HandlerMethodInfo is null");
        //TODO 检查
        mappingLookup.put(service, handlerMethodInfo);
    }


    public Map<String, HandlerMethodInfo> getMappings() {
        return this.mappingLookup;
    }


    public HandlerMethodInfo getMapingByService(String service) {
        return mappingLookup.get(service);
    }



}
