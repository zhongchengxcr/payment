package com.totoro.pay.api.response;

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
public class TradeStateResponse extends BizApiResultResponse {

    /**
     *  交易状态
     *	String(32)
     * 	SUCCESS—支付成功REFUND—转入退款NOTPAY—未支付CLOSED—已关闭PAYERROR—支付失败(其他原因，如银行返回失败)
     */
    private String trade_state;
}
