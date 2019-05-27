package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
    @Modifying//涉及的增删改都需要添加这个Modifying注解，这个可能是解决多线程出现的问题？
    @Query(value = "update tb_article set thumbup=thumbup+1 where id = ?",nativeQuery = true)
   public void thumbup(String articleId);

    @Modifying
    @Query(value = "update tb_article set state=1 where id = ?",nativeQuery = true)
   public void examinate(String articleId);

}
