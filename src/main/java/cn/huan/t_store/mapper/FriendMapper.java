package cn.huan.t_store.mapper;

import cn.huan.t_store.entity.Friend;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: t_store
 * @ClassName: FriendMapper
 * @Author: YYH
 * @Description: xxx
 * @CreateDate: 2021/5/7 11:14
 */
public interface FriendMapper {
    /**
     * 好友添加
     * @param
     * @return
     */
    Integer insertFriend(
            @Param("uid") Integer uid,
            @Param("friendname") String friendname,
            @Param("friendship") String friendship);

    /**
     * 根据用户名获取某个对象
     * @param name
     * @return
     */
    Friend getFriend(String name);

}
