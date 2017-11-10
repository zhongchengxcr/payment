package com.totoro.pay.api.requert;

/**
 * 说明 . <br>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2017/11/03 下午10:29
 * <p>
 * Company: xxx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public abstract class BaseApiResultRequest {

    //接口类型 String(32)
    protected String service;

    //版本号 String(8)
    protected String version;

    //字符集 String(8)
    protected String charset;

    //签名方式 String(8)
    protected String sign_type;

    //商户号 String(32)
    protected String mch_id;

    //随机字符串 String(32)
    protected String nonce_str;

    //签名 String(32)
    private String sign;

}
