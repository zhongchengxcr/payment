package com.totoro.pay.channel.api;

/**
 * 说明 . <br>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2017/11/03 下午11:28
 * <p>
 * Company: xxx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public interface ChannelConfigStore {

    <T> T getConfigByMerChantNo(Long merchanNo, Class<T> clzz);
}
