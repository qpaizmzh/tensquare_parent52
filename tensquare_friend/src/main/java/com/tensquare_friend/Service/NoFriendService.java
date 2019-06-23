package com.tensquare_friend.Service;


import com.tensquare_friend.DAO.FriendDao;
import com.tensquare_friend.DAO.NoFriendDao;
import com.tensquare_friend.Entity.NoFriend;
import com.tensquare_friend.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoFriendService {

    @Autowired
    private NoFriendDao nofriendDao;

    @Autowired
    private UserClient userClient;

    @Autowired
    private FriendDao friendDao;


    public int addNoFriend(String userid, String friendid) {
        //如果已经添加过了这个非好友，就不再添加
        if (nofriendDao.selectCount(userid, friendid) > 1) {
            return 0;
        }

        //向表中添加记录
        NoFriend nofriend = new NoFriend();
        nofriend.setUserid(userid);
        nofriend.setFriendid(friendid);
        nofriendDao.save(nofriend);
        friendDao.delete(userid,friendid);
        userClient.updateFansAndFollowcount(userid, friendid, -1);

        return 1;
    }

}
