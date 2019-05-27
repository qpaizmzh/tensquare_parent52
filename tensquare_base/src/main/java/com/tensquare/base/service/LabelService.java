package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> getAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void add(Label label) {
        label.setId(String.valueOf(idWorker.nextId()));
        labelDao.save(label);
    }

    public void update(Label label) {
        //传过来的对象包含了ID的话，会直接进行更新，没有的话，就会新创建一个
        labelDao.save(label);
    }


    public void delete(String id) {
        labelDao.deleteById(id);
    }


    public List<Label> searchAll(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /***
             *
             * @param root  根对象，会根据反射机制获取传过来的对象的属性对应的值
             * @param criteriaQuery 这个用于特殊的排序专用，比如是order by之类的，很少用
             * @param criteriaBuilder 这个是用于封装查询条件用的工具类
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                //相当于添加条件 labelname like '%??'
                if (label.getLabelname() != null && "" != label.getLabelname()) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname());

                    predicates.add(predicate);
                }

                //相当于添加条件 state =？
                if (label.getState() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    predicates.add(predicate);
                }
                Predicate[] arr = new Predicate[predicates.size()];
                predicates.toArray(arr);

                return criteriaBuilder.and(arr);//使用and可以拼接多个predicate对象，相当于拼接多个查询条件
            }
        });
    }

    public Page<Label> searchAll(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return labelDao.findAll(new Specification<Label>() {
            /***
             *
             * @param root  根对象，会根据反射机制获取传过来的对象的属性对应的值
             * @param criteriaQuery 这个用于特殊的排序专用，比如是order by之类的，很少用
             * @param criteriaBuilder 这个是用于封装查询条件用的工具类
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                //相当于添加条件 labelname like '%??'
                if (label.getLabelname() != null && "" != label.getLabelname()) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname());

                    predicates.add(predicate);
                }

                //相当于添加条件 state =？
                if (label.getState() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    predicates.add(predicate);
                }
                Predicate[] arr = new Predicate[predicates.size()];
                predicates.toArray(arr);

                return criteriaBuilder.and(arr);//使用and可以拼接多个predicate对象，相当于拼接多个查询条件
            }
        }, pageable);


    }
}
