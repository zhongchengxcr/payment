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
public class QueryRefundRequest extends BaseApiResultRequest {

    /**
     * 平台退款单号refund_id、out_refund_no、out_trade_no 、transaction_id 四个参数必填一个，
     * 如果同事存在优先级为：refund_id>out_refund_no>transaction_id>out_trade_no
     */

    //商户订单号,否	String(32)	商户系统内部的订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String out_trade_no;

    //平台订单号 否 String(32)	平台订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String transaction_id;

    // 商户退款单号 String(32)	商户退款单号，32个字符内、可包含字母,确保在商户系统唯一。
    private String out_refund_no;

    //平台退款单号 String(32)
    private String refund_id;
}
