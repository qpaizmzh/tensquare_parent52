package com.tensquare.bean;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "ab")
public class Customer3 {

    @RabbitHandler
    public void getMsg(String msg) {
        System.out.println("ab " + msg);
    }
}
