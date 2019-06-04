package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(Comment comment) {
        comment.set_id(new IdWorker().nextId() + "");
        commentDao.save(comment);
    }

    public List<Comment> findByArticleId(String articleid) {
        return commentDao.findByArticleid(articleid);
    }

    public void delete(String commentId) {
        //使用mongo模板进行删除
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(commentId));
        mongoTemplate.remove(query,"comment");//记得给mongodb创建一个叫“comment”的集合（也就是数据表）
    }
}
