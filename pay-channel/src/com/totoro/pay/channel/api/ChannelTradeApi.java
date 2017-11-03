package com.totoro.pay.channel.api;

import com.totoro.pay.channel.api.request.BaseChannelTradeRequest;
import com.totoro.pay.channel.api.response.BaseChannelTradeResponse;

/**
 * 说明 . <br>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2017/11/03 下午11:00
 * <p>
 * Company: xxx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public interface ChannelTradeApi {

    BaseChannelTradeResponse pay(BaseChannelTradeRequest request);

    BaseChannelTradeResponse refund(BaseChannelTradeRequest request);

    BaseChannelTradeResponse queryPay(BaseChannelTradeRequest request);

    BaseChannelTradeResponse queryRefund(BaseChannelTradeRequest request);

    BaseChannelTradeResponse close(BaseChannelTradeRequest request);
}
