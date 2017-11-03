package com.totoro.pay.api.service;

import com.totoro.pay.api.requert.BaseApiResultRequest;
import com.totoro.pay.api.requert.NoticeBodyWarpper;
import com.totoro.pay.api.response.BaseApiResultResponse;

/**
 * 说明 . <br>
 * 交易api
 * <p>
 * Copyright: Copyright (c) 2017/11/03 下午10:07
 * <p>
 * Company: xxx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public interface TradeApi {

    BaseApiResultResponse pay(BaseApiResultRequest request);

    BaseApiResultResponse refund(BaseApiResultRequest request);

    BaseApiResultResponse queryPay(BaseApiResultRequest request);

    BaseApiResultResponse queryRefund(BaseApiResultRequest request);

    BaseApiResultResponse close(BaseApiResultRequest request);

    void notice(NoticeBodyWarpper object);

}
