package cn.huan.t_store.controller;

import cn.huan.t_store.service.BuyNowService;
import cn.huan.t_store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @program: t_store
 * @description: 立即购买
 * @author: 袁银欢
 * @createTime: 2021/02/08 15:24
 **/
@RestController
@RequestMapping("buyNow")
public class BuyNowController extends BaseController{
    @Autowired
    private BuyNowService buyNowService;

    @RequestMapping("insert")
    public JsonResult<Void> insertBuyNowPro(Integer pid, Integer num, HttpSession session) {
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        buyNowService.insertBuyNowPro(uid, pid, num);
        return new JsonResult<>(OK);
    }
}
