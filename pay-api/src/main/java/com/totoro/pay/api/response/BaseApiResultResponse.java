package com.totoro.pay.api.response;

import com.totoro.pay.api.PayApiConstans;

/**
 * 说明 . <br>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2017/11/03 下午10:09
 * <p>
 * Company: xxx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public class BaseApiResultResponse {


    /**
     * 版本号 版本号，version默认值是1.0。
     */
    protected String version= PayApiConstans.PAY_API_GLOBAL_VERSION;

    /**
     * 字符集 可选值 UTF-8 ，默认为 UTF-8。
     */
    protected String charset=PayApiConstans.PAY_API_GLOBAL_DEFAULT_CHARTSET;

    /**
     * 签名类型，取值：MD5默认：MD5
     */
    protected String sign_type=PayApiConstans.PAY_API_GLOBAL_DEFAULT_SIGN_TYPE;


    /**
     * 0表示成功，非0表示失败此字段是通信标识，非交易标识，交易是否成功需要查看 result_code 来判断
     */
    protected String status;

    /**
     * 返回信息，如非空，为错误原因签名失败参数格式校验错误
     */
    protected String message;






}
