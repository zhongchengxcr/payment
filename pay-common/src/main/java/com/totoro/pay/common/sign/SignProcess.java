package com.totoro.pay.common.sign;

/**
 * 说明 . <br>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2017/11/03 下午10:10
 * <p>
 * Company: xxx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public interface SignProcess<SIGN_INPUT_TYPE, VALIDATE_INPUT_TYPE, KEY_TYPE> {

    String sign(SIGN_INPUT_TYPE param, KEY_TYPE key);

    boolean validate(VALIDATE_INPUT_TYPE res, KEY_TYPE key);
}
