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
public class AliPayAdapter implements ChannelPayProcess {

    @ChannelMapping("pay.app")
    public void pay() {

    }

    @ChannelMapping("refund.app")
    public void refund() {

    }


    @ChannelMapping("query.app")
    public void query() {

    }

}
