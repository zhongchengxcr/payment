package com.totoro.pay.routing.util;

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
public class StringUtils extends org.springframework.util.StringUtils {

    public static final String BLANK = "";

    public static String ignoreEmpty(String str) {
        return isEmpty(str) ? BLANK : str.trim();
    }


    public static String head(String str) {
        if (isEmpty(str)) {
            return str;
        }

        return str.substring(0, 1);
    }

    public static String tails(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(1, str.length());
    }

}
