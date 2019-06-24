package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseClient;
import entitys.Result;
import entitys.StatusCode;
import org.springframework.stereotype.Component;


//创建的一个熔断器涉及的类
@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findAll() {
        return new Result(false,StatusCode.ERROR,"熔断器启动");
    }
}
