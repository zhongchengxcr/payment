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
public class NoticeBody {

    /**
     * 版本号，version默认值是2.0。 	String(8)
     */
    private String version;

    /**
     * 	可选值 UTF-8 ，默认为 UTF-8。 String(8)
     */
    private String charset;

    /**
     * 签名类型，取值：MD5默认：MD5 	String(8)
     */
    private String sign_type;

    /**
     * 	0表示成功，
     * 	非0表示失败此字段是通信标识，
     * 	非交易标识，
     * 	交易是否成功需要查看 result_code 来判断 String(16)
     */
    private String status;

    /**
     * 返回信息，如非空，为错误原因签名失败参数格式校验错误 String(128)
     */
    private String message;

    /**
     * 	0表示成功，非0表示失败  String(16)
     */
    private String result_code;

    /**
     * 商户号，由平台分配  	String(32)
     */
    private String mch_id;

    /**
     * 	终端设备号  String(32)
     */
    private String device_info;

    /**
     * 随机字符串，不长于 32 位 String(32)
     */
    private String nonce_str;

    /**
     * 	参考错误码  String(32)
     */
    private String err_code;


    /**
     * 	结果信息描述 String (128)
     */
    private String err_msg;


    /**
     * 	MD5签名结果，详见“安全规范” String(32)
     */
    private String sign;

    /**
     * 用户在商户 appid 下的唯一标识 String(128)
     */
    private String openid;


    /**
     * pay.weixin.app 	String(32)
     */
    private String trade_type;


    /**
     * 支付结果：0—成功；其它—失败
     */
    private String pay_result;

    /**
     * 支付结果信息，支付成功时为空 	String(64)
     */
    private String pay_info;

    /**
     * 平台交易号 String(32)
     */
    private String transaction_id;

    /**
     * 如：微信支付单号，支付宝支付单号 	String(32)
     */
    private String out_transaction_id;

    /**
     * 商户系统内部的定单号，32个字符内、可包含字母 String(32)
     */
    private String out_trade_no;

    /**
     * 总金额，以分为单位，不允许包含任何字、符号
     */
    private int total_fee;

    /**
     * 货币类型，符合 ISO 4217 标准的三位字母代码，默认人民币：CNY 	String(8)
     */
    private String fee_type;

    /**
     * 附加信息 String(127)
     */
    private String attach;

    /**
     * 付款银行 	String(16)
     */
    private String bank_type;


    /**
     * 支付完成时间，格式为yyyyMMddHHmmss，
     * 如2009年12月27日9点10分10秒表示为20091227091010。
     * 时区为GMT+8 beijing。该时间取自平台服务器 String(14)
     */
    private String time_end;


    /**
     * success	处理成功，平台收到此结果后不再进行后续通知
     *
     * fail或其它字符 处理不成功，平台收到此结果或者没有收到任何结果，系统通过补单机制再次通知
     *
     */
}
