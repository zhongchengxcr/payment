package com.totoro.pay.channel.api;

/**
 * 说明 . <br>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2017/11/03 下午11:05
 * <p>
 * Company: xxx
 * <p>
 *
 * @author zhongcheng_m@yeah.net
 * @version 1.0.0
 */
public interface SignProcess<GET_INPUT_TYPE, VALIDATE_INPUT_TYPE, KEY_TYPE> {

    enum SignType {

        MD5, HMACSHA256, RSA, RSA2;


    }

    String get(GET_INPUT_TYPE param, KEY_TYPE key, SignType signType);

    boolean validate(VALIDATE_INPUT_TYPE param, KEY_TYPE key, SignType signType);
}
