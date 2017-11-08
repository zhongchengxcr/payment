package com.totoro.pay.controller;


import com.totoro.pay.channel.channel.ChannelPayProcess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(value = "hello")
public class Demo {

    @Autowired
    private Map<String, ChannelPayProcess> channelas;

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String hello(){
        return "hello !";
    }


    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(method = RequestMethod.GET,value = "/hello/pop")
    public String pop(){
        return "hello !";
    }
}
