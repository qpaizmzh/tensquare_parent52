package com.tensquare_friend.DAO;


import com.tensquare_friend.Entity.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoFriendDao extends JpaRepository<NoFriend, String> {
    /***
     * 根据用户id和被关注用户的id查询相关的记录数
     * @param userid
     * @param friendid
     * @return
     */
    @Query("select count(f) from NoFriend f where f.userid=?1 and f.friendid=?2")
    public int selectCount(String userid, String friendid);

    /***
     * 添加好友的时候自动删除非好友列表的数据
     * @param userid
     * @param friendid
     */
    @Query("delete from NoFriend f  where f.userid=?1 and f.friendid=?2")
    public void updateLike(String userid, String friendid);
}
