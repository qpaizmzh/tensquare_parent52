package com.tensquare_friend.DAO;

import com.tensquare_friend.Entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendDao extends JpaRepository<Friend, String> {

    /***
     * 根据用户id和被关注用户的id查询相关的记录数
     * @param userid
     * @param friendid
     * @return
     */
    @Query(value = "select count(1) from tb_friend tf where tf.userid =:userid and tf.friendid = :friendid ",nativeQuery = true)
    public int selectCount(@Param(value = "userid") String userid, @Param(value = "friendid") String friendid);

    /***
     * 更新为互相喜欢
     * @param userid
     * @param friendid
     * @param isLike
     */
    @Modifying
    @Query("update Friend f set f.islike = ?3 where f.userid=?1 and f.friendid=?2")
    public void updateLike(String userid, String friendid, String isLike);


    @Modifying
    @Query(value = "delete from tb_friend where userid=?1 and friendid=?2",nativeQuery = true)
    public void delete(String userid, String friendid);
}
