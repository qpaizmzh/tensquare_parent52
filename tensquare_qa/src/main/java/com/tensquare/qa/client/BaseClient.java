package com.tensquare.qa.client;

import com.tensquare.qa.client.impl.BaseClientImpl;
import entitys.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "tensquare-base",path = "/label",fallback = BaseClientImpl.class)
public interface BaseClient {

    //本质上这是用过网络间的传输进行调用的
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll();
    //另外包含参数的时候，@Pathvariable的value值必须要填，并且要和访问路径的带参变量对应
    //因为默认取方法参数名字作为路径的变量名的设置已经无效

}
