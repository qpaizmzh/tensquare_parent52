package com.tensquare.base.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    @Value("${sms.message}")
    private String message;

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public String ip() {
        return message;
    }
}
