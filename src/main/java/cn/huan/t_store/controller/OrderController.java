package cn.huan.t_store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.huan.t_store.entity.Order;
import cn.huan.t_store.service.OrderService;
import cn.huan.t_store.util.JsonResult;



@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("create")
	public JsonResult<Order> showOrder(Integer aid,Integer[] cids,HttpSession session){
		JsonResult<Order> jsonResult = new JsonResult<Order>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		Order data = orderService.createOrder(uid, aid, cids, username);
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}
}
