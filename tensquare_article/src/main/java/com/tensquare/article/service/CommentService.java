package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.IdWorker;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    public void add(Comment comment) {
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }
}
