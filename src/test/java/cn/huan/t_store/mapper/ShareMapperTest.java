package cn.huan.t_store.mapper;

import cn.huan.t_store.entity.Share;
import cn.huan.t_store.entity.ShareVO;
import cn.huan.t_store.entity.User;
import cn.huan.t_store.service.ShareService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
* @program: t_store
* @description: xxx
* @author: 袁银欢
* @createTime: 2021/02/02 18:22
**/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareMapperTest {

    @Autowired
    private ShareMapper shareMapper ;

    @Autowired
    private ShareService shareService;

    @Test
    public void shareAdd() {
        Share share = new Share();
        share.setUid(6);
        share.setProid(100005);
        share.setSharer("YYH");
        share.setSharetime(new Date());
        Integer integer = shareMapper.shareAdd(share);
        System.err.println(integer);
    }

    @Test
    public void listShow() {
        List<ShareVO> shareVOS = shareMapper.listShares(7);
        System.err.println(shareVOS);
    }

    @Test
    public void listUser() {
        List<User> users = shareService.listUser();
        System.err.println(users);
    }

}
