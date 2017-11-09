package com.totoro.pay.routing.mapping;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
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
public class SimpleRoutingManger implements RoutingManger {

    @Autowired
    private Set<HandlerAdapter> adapters;

    private Map<String, HandlerAdapter> adapterCache = new ConcurrentHashMap<>();

    @Autowired
    private HandlerMapping handlerMapping;

    @Override
    public Object routing(String service, Object... req) {

        Preconditions.checkArgument(!StringUtils.isEmpty(service), "Service is empty!");

        Object handlerDefinition = handlerMapping.getHandler(service);

        Preconditions.checkNotNull(handlerDefinition, "No matching handler was found!");
        HandlerAdapter handlerAdapter;
        if (adapterCache.containsKey(service)) {
            handlerAdapter = adapterCache.get(service);
        } else {
            List<HandlerAdapter> adapterList = adapters.stream().filter((a) -> a.supports(handlerDefinition)).collect(Collectors.toList());
            Preconditions.checkArgument(!CollectionUtils.isEmpty(adapterList), "No matching adapter was found!");
            handlerAdapter = adapterList.get(0);
            adapterCache.put(service, handlerAdapter);
        }

        return handlerAdapter.handle(handlerDefinition, req);
    }
}
