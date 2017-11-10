package com.totoro.pay.common.sign.md5;


import com.totoro.pay.common.sign.StringKeyGenericityMapStringSignProcess;
import com.totoro.pay.common.util.MD5;
import com.totoro.pay.common.util.SignUtils;

import java.util.Map;

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
public class SimpleMd5QueryStringSignProcess implements StringKeyGenericityMapStringSignProcess<String, String> {


    @Override
    public String sign(Map<String, String> param, String key) {
        Map<String, String> params = SignUtils.paraFilter(param);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
        return sign;
    }

    @Override
    public boolean validate(Map<String, String> param, String key) {
        return SignUtils.checkParam(param, key);
    }


}
