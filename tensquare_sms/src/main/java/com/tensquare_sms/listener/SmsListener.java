package com.tensquare_sms.listener;

import com.tensquare_sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "忘了是什么队列了")
public class SmsListener {

    @Autowired
    private SmsUtil utils;

    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;


    @RabbitHandler
    public void getMSg(Map<String, String> maps) {
        System.out.println("手机号是：" + maps.get("mobile"));
        System.out.println("验证码是：" + maps.get("code"));
    }
}
