package com.totoro.pay.controller;


import com.totoro.pay.routing.mapping.RoutingManger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "hello")
public class Demo {


    @Autowired
    private RoutingManger routingManger;


    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String hello(){

        Object obj = routingManger.routing("wx.refund.app","zhongc","asdasd");

         obj = routingManger.routing("wx.refund.app");

        obj = routingManger.routing("wx.pay.app","zhongc","sdfsdf","sdfsdf");

        obj = routingManger.routing("wx.pay.app","zhongc");

        obj = routingManger.routing("wx.pay.app");

        System.out.println(obj);
        return "hello !";
    }


    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(method = RequestMethod.GET,value = "/hello/pop")
    public String pop(){
        return "hello !";
    }
}
