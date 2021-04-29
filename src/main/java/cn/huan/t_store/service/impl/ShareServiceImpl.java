package cn.huan.t_store.service.impl;

import cn.huan.t_store.entity.Share;
import cn.huan.t_store.entity.ShareVO;
import cn.huan.t_store.entity.User;
import cn.huan.t_store.mapper.ShareMapper;
import cn.huan.t_store.mapper.UserMapper;
import cn.huan.t_store.service.ShareService;
import cn.huan.t_store.service.ex.InsertException;
import cn.huan.t_store.service.ex.UserNotFoundException;
import cn.huan.t_store.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
* @program: t_store
* @description: 业务实现类
* @author: 袁银欢
* @createTime: 2021/02/03 08:44
**/
@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShareMapper shareMapper;

    /**
     * 新增分享业务
     *  1. 先根据用户传入的用户名查询实体,存在则封装
     * @param proId 商品ID
     * @param username  被分享者
     * @param sharer 分享者【当前用户】
     */
    @Override
    public void insertShare(Integer proId, String username, String sharer, Integer uid) {
        // 根据uId查询用户是否存在,不存在则抛出异常
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户未找到, 请重新输入！");
        }
        Share share = new Share();
        // 如果分享用户为当前用户则抛出异常
        if (user.getUid().equals(uid)) {
            throw new UsernameDuplicateException("不可以给自己分享，清重新填写！");
        }
        // 分享用户存在, 则获取到分享用户的id
        share.setUid(user.getUid());
        share.setProid(proId);
        share.setSharer(sharer);
        share.setSharetime(new Date());
        System.err.println(sharer);

        Integer result = shareMapper.shareAdd(share);
        if (result != 1) {
            throw new InsertException("分享失败！分享用户数据时出现未知错误，请联系系统管理员！");
        }
    }


    /**
     * 查询分享【根据当前用户的id为条件查询】
     * @param uid
     * @return
     */
    @Override
    public List<ShareVO> listShares(Integer uid) {
        List<ShareVO> shareVOS = shareMapper.listShares(uid);
        if (CollectionUtils.isEmpty(shareVOS)){
            throw new UserNotFoundException("无任何分享商品！");
        }
        return shareVOS;
    }


    /**
     * 显示用户好友
     * @return
     */
    @Override
    public List<User> listUser() {
        List<User> list = shareMapper.listUsername();
        if (CollectionUtils.isEmpty(list)) {
            throw new UserNotFoundException("无好友分享！");
        }
        return list;
    }
}
