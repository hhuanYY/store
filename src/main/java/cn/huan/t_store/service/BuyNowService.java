package cn.huan.t_store.service;


/**
 * @program: t_store
 * @description: 立即购买
 * @author: 袁银欢
 * @createTime: 2021/02/08 15:11
 **/
public interface BuyNowService {
    /**
     * 立即购买
     * @param uid
     * @param pid
     * @param num
     */
    void insertBuyNowPro(Integer uid, Integer pid, Integer num);

}
