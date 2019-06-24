package com.tensquare_friend.client;

import entitys.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "tensquare-user",path = "/user")
public interface UserClient {

    @RequestMapping(value = "/updatefansandfollow/{userid}/{friendid}/{x}", method = RequestMethod.PUT)
    public Result updateFansAndFollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid,@PathVariable("x") int x);
}
