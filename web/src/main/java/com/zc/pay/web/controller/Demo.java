package com.zc.pay.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "hello")
public class Demo {


    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String hello(){
        return "hello !";
    }
}
