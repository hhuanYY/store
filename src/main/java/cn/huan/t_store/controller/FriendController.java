package cn.huan.t_store.controller;

import cn.huan.t_store.entity.Friend;
import cn.huan.t_store.service.FriendService;
import cn.huan.t_store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ProjectName: t_store
 * @ClassName: FriendController
 * @Author: YYH
 * @Description: xx
 * @CreateDate: 2021/5/7 11:27
 */
@RestController
@RequestMapping("friends")
public class FriendController extends BaseController {

    @Autowired
    private FriendService friendService;

    @RequestMapping("add")
    public JsonResult<Void> insertShare(String friendname, String friendship, HttpSession session) {
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        friendService.insertFriend(uid, friendname, friendship);
        return new JsonResult<>(OK);
    }
}
