package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid=?", nativeQuery = true)
    public Page<Problem> getQAlist(String labelID, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid=? order by reply desc", nativeQuery = true)
    public Page<Problem> getHotList(String label, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid=? and (reply is null or reply = 0)", nativeQuery = true)
    public Page<Problem> getwaitList(String label, Pageable pageable);
}
