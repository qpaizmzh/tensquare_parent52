package com.tensquare_friend.Controller;

import com.tensquare_friend.Service.FriendService;
import com.tensquare_friend.Service.NoFriendService;
import com.tensquare_friend.inteceptor.JwtInteceptor;
import entitys.Result;
import entitys.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private JwtInteceptor inteceptor;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService service;

    @Autowired
    private NoFriendService noFriendService;

    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        //验证登录的是否是正常的Id
        Claims claims_user = (Claims) request.getAttribute("user_claims");
        if (claims_user == null) {
            return new Result(false, StatusCode.LOGINERROR, "权限不足");
        }

        //判断类型
        String userid = claims_user.getId();
        //type为1的就是添加好友，2的为非好友
        if (type != null) {
            if (type.equals("1")) {
                //添加好友
                if (service.addFriend(userid, friendid) == 0) {
                    return new Result(false, StatusCode.REPERROR, "已经添加为好友");
                }
            } else if (type.equals("2")) {
                //添加黑名单
                noFriendService.addNoFriend(userid, friendid);
            }

            return new Result(true, StatusCode.OK, "操作成功");
        } else {
            return new Result(false, StatusCode.ERROR, "参数异常");
        }
    }


}
