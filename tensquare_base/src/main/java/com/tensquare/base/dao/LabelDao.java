package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//继承JPA接口，可以操作一些简单CRUD
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {

}
