package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import java.util.List;

@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate template;

    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit findById(String id) {
        //findById返回的是Optional对象，这是个用于防止出现空指针异常专用的工具类，可以通过它返回对应的对象
        return spitDao.findById(id).get();
    }

    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spitDao.save(spit);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void delete(String id) {
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentId(String parentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentId, pageRequest);
    }


    public void thumbup(String spitId) {


        //第一种方式，和数据库交互两次
//        Spit spit = spitDao.findById(spitId).get();
//        Integer thumb = spit.getThumbup();
//        if (spit.getThumbup() != null) {
//            spit.setThumbup(thumb + 1);
//        } else {
//            spit.setThumbup(1);
//        }
//
//        spitDao.save(spit);


        //第二种方式，使用mongoDb的进行原生更新,只跟数据库交互一次
        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("_id").is("5cf34724c3c25609e8e8d8d8"));
        update.inc("thumbup", 1);//自动+1的方法
        template.updateFirst(query,update,"spit");

    }
}
