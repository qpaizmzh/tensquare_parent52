package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;

import entitys.Result;
import entitys.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return new Result(true, StatusCode.OK, "提交成功");
    }
}
