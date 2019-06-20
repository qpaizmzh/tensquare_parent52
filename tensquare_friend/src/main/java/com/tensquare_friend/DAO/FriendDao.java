package com.tensquare_friend.DAO;

import com.tensquare_friend.Entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend, String> {

    /***
     * 根据用户id和被关注用户的id查询相关的记录数
     * @param userid
     * @param friendid
     * @return
     */
    @Query("select count(f) from Friend f where f.userid=?1 and f.friendid=?2")
    public int selectCount(String userid, String friendid);

    /***
     * 更新为互相喜欢
     * @param userid
     * @param friendid
     * @param isLike
     */
    @Modifying
    @Query("update Friend f set f.islike = ?3 where f.userid=?1 and f.friendid=?2")
    public void updateLike(String userid, String friendid, String isLike);
}
