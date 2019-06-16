package com.tensquare_sms.Listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare_sms.SmsUtils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "msgs")
public class SmsListener {

    @Autowired
    private SmsUtil utils;

    @Value(value = "${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value(value = "${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;

    @RabbitHandler
    public void getMsg(Map<String,String> maps){
        System.out.println("mobile "+ maps.get("mobile"));
        System.out.println("code "+ maps.get("random"));

        try {
            //短信内容要换成JSON串,这里的checkcode键是要给开通短信网站的模板使用的，那里的使用模板会有个${checkcode}的表达式存在，用来调用下面准备发送过去的JSON串的
            utils.sendSms(maps.get("mobile"),accessKeyId,accessKeySecret,"{\"checkcode\":\""+maps.get("random")+"\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
