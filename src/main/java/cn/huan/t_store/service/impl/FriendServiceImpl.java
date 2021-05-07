package cn.huan.t_store.service.impl;

import cn.huan.t_store.entity.Friend;
import cn.huan.t_store.entity.User;
import cn.huan.t_store.mapper.FriendMapper;
import cn.huan.t_store.mapper.UserMapper;
import cn.huan.t_store.service.FriendService;
import cn.huan.t_store.service.UserService;
import cn.huan.t_store.service.ex.UserNotFoundException;
import cn.huan.t_store.service.ex.UserNotMatchingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: t_store 
 * @ClassName: FriendServiceImpl
 * @Author: YYH
 * @Description: xxx  
 * @CreateDate: 2021/5/7 11:23
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertFriend(Integer uid, String friendname, String friendship) {
        User result = userMapper.findByUsername(friendname);
        if (result == null) {
            throw new UserNotFoundException("该用户不存在，请重新输入...");
        }

        Integer integer = friendMapper.insertFriend(uid, friendname, friendship);
        if (integer != 1) {
            throw new UserNotMatchingException("好友添加失败... 请联系系统管理员！");
        }
    }
}
