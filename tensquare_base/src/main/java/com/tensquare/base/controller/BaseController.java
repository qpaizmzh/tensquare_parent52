package com.tensquare.base.controller;

import entitys.Result;
import entitys.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//在处理某些异常的时候，可以使用advice注解来处理抛出的异常，比如像这里的同样封装一个Result，
//把错误的信息返回到规定的对象，规范返回的格式
@RestControllerAdvice
public class BaseController {
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
