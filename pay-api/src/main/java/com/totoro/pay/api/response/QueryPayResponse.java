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
public class QueryPayResponse extends TradeStateResponse {

    /**
     * 交易类型
     * String(32)
     * pay.weixin.native
     */
    private String trade_type;

    /**
     * 服务商公众号appid
     * String(32)
     */
    private String  appid;

    /**
     * 用户在服务商 appid 下的唯一标识
     * 用户标识 openid
     * String(128)
     */
    private String  openid;

    /**
     * 用户是否关注服务商公众账号，Y-关注，N-未关注
     * String(1)
     */
    private String  is_subscribe;

    /**
     * 平台订单号
     * String(32)
     */
    private String  transaction_id;

    /**
     * 第三方支付单 如：微信支付单号，支付宝支付单号
     * String(32)
     */
    private String  out_transaction_id;

    /**
     * 商户订单号 商户系统内部的定单号，32个字符内、可包含字母
     * String(32)
     */
    private String  out_trade_no;


    /**
     * 总金额，以分为单位，不允许包含任何字、符号
     */
    private int total_fee;


    /**
     * 现金券支付金额<=订单总金额， 订单总金额-现金券金额为现金支付金额
     */
    private int  coupon_fee;


    /**
     * 货币类型，符合 ISO 4217 标准的三位字母代码，默认人民币：CNY
     * String(8)
     */
    private String  fee_type;

    /**
     * 附加信息 商家数据包，原样返回预下单时自定义数据
     * String(127)
     */
    private String  attach;

    /**
     * 付款银行  银行类型
     * String(16)
     */
    private String  bank_type;

    /**
     * 银行订单号 银行订单号，若为微信支付则为空
     * String(32)
     */
    private String  bank_billno;

    /**
     * 支付完成时间
     * 支付完成时间，格式为yyyyMMddHHmmss，
     * 如2009年12月27日9点10分10秒表示为20091227091010。
     * 时区为GMT+8 beijing。该时间取自平台服务器
     */
    private String  time_end;
}
