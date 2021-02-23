package cn.huan.t_store.service.impl;

import cn.huan.t_store.entity.BuyNow;
import cn.huan.t_store.entity.Product;
import cn.huan.t_store.mapper.BuyNowMapper;
import cn.huan.t_store.mapper.ProductMapper;
import cn.huan.t_store.service.BuyNowService;
import cn.huan.t_store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @program: t_store
* @description: 立即购买
* @author: 袁银欢
* @createTime: 2021/02/08 15:13
**/
@Service
public class BuyNowServiceImpl implements BuyNowService {

    @Autowired
    private BuyNowMapper buyNowMapper;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public void insertBuyNowPro(Integer uid, Integer pid, Integer num) {
        BuyNow buyNow = new BuyNow();
        buyNow.setUid(uid);
        buyNow.setPid(pid);
        buyNow.setNum(num);
        Product product = productMapper.findById(pid);
        buyNow.setPrice(Math.toIntExact(product.getPrice()));
        Integer integer = buyNowMapper.insertBuyNowPro(buyNow);
        if (integer != 1) {
            throw new InsertException("系统出错,请联系管理员！");
        }
    }
}
