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
public class BillRequest extends BaseApiResultRequest {

    /**
     * 格式：yyyyMMdd(如：20150101)
     * String(8)
     */
    private String bill_date;


    /**
     * 账单类型 String(8)
     * ALL：全部;
     * SUCCESS：成功;
     * REFUND：退款
     */
    private String bill_type;


}
