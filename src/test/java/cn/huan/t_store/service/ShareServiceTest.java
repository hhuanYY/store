package cn.huan.t_store.service;

import cn.huan.t_store.entity.BuyNow;
import cn.huan.t_store.entity.Product;
import cn.huan.t_store.entity.ShareVO;
import cn.huan.t_store.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
* @program: t_store
* @description: xxx
* @author: 袁银欢
* @createTime: 2021/02/03 09:28
**/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareServiceTest {
    @Autowired
    private ShareService shareService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BuyNowService buyNowService;

    @Test
    public void share() {
        shareService.insertShare(10000088, "root", "HANHAN", 7);
    }

    @Test
    public void listShare() {
        List<ShareVO> shareVOS = shareService.listShares(4);
        System.err.println(shareVOS);
    }

    @Test
    public void hotSort() {
        List<Product> hotSort = productMapper.findHotSort();
        System.err.println(hotSort);
    }

    @Test
    public void bynows() {
        buyNowService.insertBuyNowPro(1, 2, 3);
        System.err.println("OK");
    }

}
