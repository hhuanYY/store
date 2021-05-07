package cn.huan.t_store.service;

import cn.huan.t_store.entity.Friend;
import cn.huan.t_store.entity.ShareVO;
import cn.huan.t_store.entity.User;

import java.util.List;

/**
 * @program: t_store
 * @description: 分享业务
 * @author: 袁银欢
 * @createTime: 2021/02/03 08:43
 **/
public interface ShareService {

    /**
     * 新增分享
     * @param proId
     * @param username
     * @param sharer
     */
    void insertShare(Integer proId, String username, String sharer, Integer uid);

    /**
     * 显示分享
     * @param uid
     * @return
     */
    List<ShareVO> listShares(Integer uid);


    /**
     * 显示用户好友
     * @return
     */
    List<Friend> listUser(Integer uid);
}
