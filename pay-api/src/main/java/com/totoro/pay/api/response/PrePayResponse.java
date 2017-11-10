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
public class PrePayResponse extends BizApiResultResponse {

    /**
     * 二维码链接
     * String(64)
     * 此参数可直接生成二维码展示出来进行扫码支付
     */
    private String code_url;

    /**
     * 二维码图片
     * code_img_url
     * 直接用此链接请求二维码图片
     */
    private String code_img_url;

    /**
     * 支付信息
     * Json格式的字符串，微信官方SDK中需要的参数信息
     */
    private String pay_info;

    /**
     * 商户订单号
     */
    private String out_trade_no;


    /**
     * 平台订单号
     */
    private String transaction_id;

}
