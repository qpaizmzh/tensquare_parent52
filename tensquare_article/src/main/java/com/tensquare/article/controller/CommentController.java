package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entitys.Result;
import entitys.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return new Result(true, StatusCode.OK, "评论新增成功");
    }


    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public Result getcomment(@PathVariable String articleId) {
        List<Comment> comments = commentService.findByArticleId(articleId);
        return new Result(true, StatusCode.OK, "查询成功", comments);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String commentId) {
        commentService.delete(commentId);
        return new Result(true, StatusCode.OK,"删除成功");
    }
}
