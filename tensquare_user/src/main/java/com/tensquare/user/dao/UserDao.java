package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	public User findByMobile(String mobile);

	@Modifying
	@Query(value = "update tb_user tu set tu.fanscount = tu.fanscount+:x where tu.id = :id ",nativeQuery = true)
	public void updateFans(@Param("id") String userid, @Param("x") int x);

	@Modifying
	@Query(value = "update tb_user tu set tu.followcount = tu.followcount+:x where tu.id = :id ",nativeQuery = true)
	public void updatefollow(@Param("id") String userid,@Param("x") int x);


}
