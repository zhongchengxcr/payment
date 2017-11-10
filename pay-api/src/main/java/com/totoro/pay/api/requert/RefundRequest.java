package com.totoro.pay.api.requert;

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
public class RefundRequest extends BaseApiResultRequest {

    /**
     * 商户订单号
     * String(32)
     */
    private String out_trade_no;

    /**
     * 平台订单号
     * String(32)
     */
    private String transaction_id;


    /**
     * 商户退款单号
     * String(32)
     */
    private String out_refund_no;


    /**
     * 总金额
     * Int
     */
    private int total_fee;

    /**
     * 退款金额
     * Int
     */
    private int refund_fee;


    /**
     * 操作员帐号,默认为商户号
     */
    private String op_user_id;


    /**
     * ORIGINAL原路退款，默认
     */
    private String refund_channel = "ORIGINAL";

}
