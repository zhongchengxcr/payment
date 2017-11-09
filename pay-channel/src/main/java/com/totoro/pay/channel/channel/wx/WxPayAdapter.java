package com.totoro.pay.channel.channel.wx;

import com.totoro.pay.channel.annotation.ChannelMapping;
import com.totoro.pay.channel.channel.ChannelPayProcess;
import org.springframework.stereotype.Component;

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
@Component
@ChannelMapping("wx.")
public class WxPayAdapter implements ChannelPayProcess {

    @ChannelMapping("pay.app")
    public String pay(String name,String opo) {
        System.out.println(name);
        return "Hello";
    }

    @ChannelMapping("refund.app")
    public void refund() {
        System.out.println("hello");
    }


    @ChannelMapping("query.app")
    public void query() {

    }


}
