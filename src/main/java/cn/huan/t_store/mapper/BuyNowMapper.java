package cn.huan.t_store.mapper;

import cn.huan.t_store.entity.BuyNow;

/**
* @program: t_store
* @description: 购买
* @author: 袁银欢
* @createTime: 2021/02/08 15:00
**/
public interface BuyNowMapper {
    /**
     * 立即购买
     * @param buyNow
     * @return
     */
    Integer insertBuyNowPro(BuyNow buyNow);

}
