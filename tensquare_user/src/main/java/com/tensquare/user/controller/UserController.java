package com.tensquare.user.controller;

import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entitys.PageResult;
import entitys.Result;
import entitys.StatusCode;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userService.add(user);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Claims admin_claims = (Claims) request.getAttribute("admin_claims");
        if (admin_claims != null) {
            userService.deleteById(id);
            return new Result(true, StatusCode.OK, "删除成功");
        }
        return new Result(false, StatusCode.ACCESSERROR, "没有相应的权限进行删除");


    }

    /***
     * 发送短信
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendmsgs(@PathVariable String mobile) {
        userService.sendmsgs(mobile);
        return new Result(true, StatusCode.OK, "发送成功");
    }


    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public Result register(@PathVariable String code, @RequestBody User user) {
        String realCode = redisTemplate.opsForValue().get(user.getMobile()).toString();
        if (realCode == null) {
            return new Result(false, StatusCode.ERROR, "请用手机获取你的验证码");
        }
        if (!realCode.equals(code)) {
            return new Result(false, StatusCode.ERROR, "请用手机获取你的验证码");
        } else {
            return new Result(false, StatusCode.OK, "验证成功");
        }
    }


    @RequestMapping(value = "/updatefansandfollow/{userid}/{friendid}/{x}", method = RequestMethod.PUT)
    public Result updateFansAndFollowcount(@PathVariable String userid, @PathVariable String friendid,@PathVariable int x) {
        userService.updateFansAndFollowcount(userid, friendid, x);
        return new Result(true, StatusCode.OK, "操作成功");

    }


}
