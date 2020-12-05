package cn.huan.t_store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.huan.t_store.entity.CartVO;
import cn.huan.t_store.service.CartService;
import cn.huan.t_store.util.JsonResult;



@RestController
@RequestMapping("carts")
public class CartController extends BaseController{

	@Autowired
	private CartService cartService;
	
	@RequestMapping("insert")
	public JsonResult<Void> insertToCart(Integer pid,Integer acount,HttpSession session){
		JsonResult<Void> jsonResult = new JsonResult<Void>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		cartService.addToCart(uid, pid, username, acount);
		jsonResult.setState(OK);
		return jsonResult;
	}
	
	@GetMapping("show")
	public JsonResult<List<CartVO>> showAllCarts(HttpSession session){
		JsonResult<List<CartVO>> jsonResult = new JsonResult<List<CartVO>>();
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());	
		List<CartVO> data = cartService.getAllCarts(uid);
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}
	
	//购物车自增1
	@RequestMapping("{cid}/addNum")
	public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid,HttpSession session){
		JsonResult<Integer> jsonResult = new JsonResult<Integer>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		Integer data = cartService.addNum(cid, uid, username);
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}
	
	//GET: 主要是用户获取服务器端的数据
	//控制器的返回值类型和业务层抽象方法一样即可(即能够装入查询的数据即可)
	@RequestMapping("{cid}/reduceNum")
	public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid,HttpSession session){
		JsonResult<Integer> jsonResult = new JsonResult<Integer>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		Integer data = cartService.reduceNum(cid, uid, username);
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}
	
	@GetMapping("{cid}/deleteCart")
	public JsonResult<Void> deleteCart(@PathVariable("cid") Integer cid,HttpSession session){
		JsonResult<Void> jsonResult = new JsonResult<Void>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		cartService.getDeleteByCid(cid, uid);
		jsonResult.setState(OK);
		return jsonResult;
	}
	
	
}
