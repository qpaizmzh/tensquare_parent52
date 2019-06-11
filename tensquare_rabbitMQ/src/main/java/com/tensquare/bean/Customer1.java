package com.tensquare.bean;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "it")
public class Customer1 {

    @RabbitHandler
    public void getMsg(String msg) {
        System.out.println("it " + msg);
    }
}
