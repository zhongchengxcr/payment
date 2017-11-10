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
public class BizApiResultResponse extends BaseApiResultResponse {

    /**
     * 业务结果 0表示成功，非0表示失败
     *  String(16)
     */
    private String result_code;


    /**
     * 商户号，由平台分配
     * String(32)
     */
    private String mch_id;


    /**
     * 平台支付分配的终端设备号
     * String(32)
     */
    private String device_info;


    /**
     * 随机字符串，不长于 32 位
     * String(32)
     */
    private String nonce_str;


    /**
     * 具体错误码请看错误码列表
     * String(32)
     */
    private String err_code;

    /**
     * 错误代码描述 结果信息描述
     * String(128)
     */
    private String err_msg;

    /**
     * 签名 MD5签名结果，详见“安全规范”
     * String(32)
     */
    private String sign;


}
