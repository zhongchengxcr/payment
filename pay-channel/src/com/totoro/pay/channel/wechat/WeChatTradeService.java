package com.totoro.pay.channel.wechat;

import com.totoro.pay.channel.api.ChannelTradeApi;
import com.totoro.pay.channel.api.SignProcess;
import com.totoro.pay.channel.api.request.BaseChannelTradeRequest;
import com.totoro.pay.channel.api.response.BaseChannelTradeResponse;

/**
 * 说明 . <br>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2017/11/03 下午11:03
 * <p>
 * Company: xxx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public class WeChatTradeService implements ChannelTradeApi {


    public WeChatTradeService(SignProcess signProcess) {
        this.signProcess = signProcess;
    }

    private SignProcess signProcess;


    @Override
    public BaseChannelTradeResponse pay(BaseChannelTradeRequest request) {

        return null;
    }

    @Override
    public BaseChannelTradeResponse refund(BaseChannelTradeRequest request) {
        return null;
    }

    @Override
    public BaseChannelTradeResponse queryPay(BaseChannelTradeRequest request) {
        return null;
    }

    @Override
    public BaseChannelTradeResponse queryRefund(BaseChannelTradeRequest request) {
        return null;
    }

    @Override
    public BaseChannelTradeResponse close(BaseChannelTradeRequest request) {
        return null;
    }


}
