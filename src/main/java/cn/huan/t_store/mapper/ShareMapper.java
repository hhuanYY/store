package cn.huan.t_store.mapper;

import cn.huan.t_store.entity.Share;
import cn.huan.t_store.entity.ShareVO;
import cn.huan.t_store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: t_store
 * @description: 分享
 * @author: 袁银欢
 * @createTime: 2021/02/02 18:15
 **/
public interface ShareMapper {

    /**
     * 新增分享
     * @param share
     * @return
     */
    Integer shareAdd(Share share);


    /**
     * 查询分享
     * @param uid
     * @return
     */
    List<ShareVO> listShares(Integer uid);


    /**
     * 查询姓名
     * @return 结果集
     */
    List<User> listUsername();


}
