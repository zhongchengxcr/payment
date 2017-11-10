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
public class PrePayRequest extends BaseApiResultRequest {

    //商户订单号 String(32)
    private String out_trade_no;

    //设备号 String(32)
    private String device_info;

    //商品描述 String(127)
    private String body;

    //用户openid String(128)
    private String openid;

    //公众账号或小程序ID String(32)
    private String appid;

    //附加信息 String(127)
    private String attach;

    //总金额，以分为单位，不允许包含任何字、符号
    private int total_fee;

    //终端IP	String(16)
    private int mch_create_ip;

    //通知地址 String(255)
    private String notify_url;

    //订单生成时间String(14) yyyyMMddHHmmss 同时传入会生效
    private String time_start;

    //订单超时时间 String(14) yyyyMMddHHmmss
    private String time_expire;

    //商品标记	String(32)
    private String goods_tag;

    //是否限制信用卡 	String(32)
    private String limit_credit_pay;
}
