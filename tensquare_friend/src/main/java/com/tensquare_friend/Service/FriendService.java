package com.tensquare_friend.Service;

import com.tensquare_friend.DAO.FriendDao;
import com.tensquare_friend.DAO.NoFriendDao;
import com.tensquare_friend.Entity.Friend;
import com.tensquare_friend.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserClient userClient;

    @Autowired
    private NoFriendDao noFriendDao;


    public int addFriend(String userid, String friendid) {
        //如果已经添加过了这个好友，就不再添加
        if (friendDao.selectCount(userid, friendid) > 1) {
            return 0;
        }

        //向表中添加记录
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        noFriendDao.updateLike(userid, friendid);
        userClient.updateFansAndFollowcount(userid, friendid, 1);

        //判断是否互相喜欢，如果是，将isLike互相设置为1
        if (friendDao.selectCount(friendid, userid) > 0) {
            friendDao.updateLike(userid, friendid, "1");
            friendDao.updateLike(userid, friendid, "1");
        }

        return 1;
    }


}
