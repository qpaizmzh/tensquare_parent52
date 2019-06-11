package com.tensquare_test;

import com.tensquare.rabbitMQApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = rabbitMQApplication.class)
public class rabbitTest {

    @Autowired
    private AmqpTemplate template;

    @Test
    public void test1() {
        //直接模式
        //第一个参数是routing key,在直接模式中使用于定位queue的队列名字
        //exchange转换器在直接模式中默认是“”,即任意通过
        //template.convertAndSend("it","Kawhi  leonard");

        //分裂模式
        //暂时不需要routing key
        //想要一条模式多个地方使用，需要让多个队列绑定指定的exchange绑定器才把信息分发到不同的队列中
        //template.convertAndSend("can","","kawahi leonard");

        //主题模式
        //需要routing key，相当于是一个规则，按照规则来指定这个信息到哪个消息队列
        //第一个参数填的还是exchange的名字，第二个填的就是对应的routing key
        template.convertAndSend("theme","god.log","kawahi leonard");
    }
}
