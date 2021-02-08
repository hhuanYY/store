package cn.huan.t_store.mapper;

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
}
