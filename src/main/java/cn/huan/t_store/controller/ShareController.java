package cn.huan.t_store.controller;

import cn.huan.t_store.entity.Friend;
import cn.huan.t_store.entity.ShareVO;
import cn.huan.t_store.entity.User;
import cn.huan.t_store.service.ShareService;
import cn.huan.t_store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * @program: t_store
 * @description: 分享接口
 * @author: 袁银欢
 * @createTime: 2021/02/03 09:32
 **/
@RestController
@RequestMapping("share")
public class ShareController extends BaseController {
    @Autowired
    private ShareService shareService;

    /**
     * 新增分享
     * @param proId    商品id
     * @param username 用户名
     * @param session
     * @return
     */
    @RequestMapping("add")
    public JsonResult<Void> insertShare(Integer proId, String username, HttpSession session) {
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        String sharer = session.getAttribute("username").toString();
        shareService.insertShare(proId, username, sharer, uid);
        return new JsonResult<>(OK);
    }


    /**
     * 展示分享
     * @param session
     * @return
     */
    @RequestMapping("show")
    public JsonResult<List<ShareVO>> listShare(HttpSession session) {
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        List<ShareVO> date = shareService.listShares(uid);
        return new JsonResult<>(OK, date);
    }


    /**
     * 显示好友用户
     * @return
     */
    @RequestMapping("showUsername")
    public JsonResult<List<Friend>> listUsername(HttpSession session) {
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        List<Friend> date = shareService.listUser(uid);
        return new JsonResult<>(OK, date);
    }
}
