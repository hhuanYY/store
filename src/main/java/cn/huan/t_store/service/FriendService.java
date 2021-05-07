package cn.huan.t_store.service;

import cn.huan.t_store.entity.Friend;

/**
 * @ProjectName: t_store
 * @ClassName: FriendService
 * @Author: YYH
 * @Description: xx
 * @CreateDate: 2021/5/7 11:22
 */
public interface FriendService {

    void insertFriend(Integer uid, String friendname, String friendship);
}
