package cn.huan.t_store.mapper;

import cn.huan.t_store.entity.BuyNow;
import cn.huan.t_store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
* @program: t_store
* @description: xx
* @author: 袁银欢
* @createTime: 2021/02/07 16:19
**/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BuyNowMapper buyNowMapper;


    @Test
    public void listPro() {
        List<Product> products = productMapper.listByids(10000001, 10000017, 0);
        System.err.println(products);
    }


    @Test
    public void hotSort() {
        List<Product> hotSort = productMapper.findHotSort();
        System.err.println(hotSort);
    }

    @Test
    public void like() {
        List<Product> result = productMapper.findLikeList("联想");
        System.err.println(result);
    }


    @Test
    public void bynow() {
        BuyNow buyNow = new BuyNow();
        buyNow.setNum(1);
        buyNow.setPid(100001);
        buyNow.setNum(10);
        buyNow.setPrice(38);
        Integer integer = buyNowMapper.insertBuyNowPro(buyNow);
        System.err.println(integer);
    }
}
