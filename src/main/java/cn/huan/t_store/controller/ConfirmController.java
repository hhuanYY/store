package cn.huan.t_store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.huan.t_store.entity.Address;
import cn.huan.t_store.entity.CartVO;
import cn.huan.t_store.service.ConfirmService;
import cn.huan.t_store.util.JsonResult;

@RestController
@RequestMapping("confirm")
public class ConfirmController extends BaseController{

	@Autowired
	private ConfirmService confirmService;
	
	@GetMapping("showAddress")
	public JsonResult<List<Address>> showAddress(HttpSession session){
		JsonResult<List<Address>> jsonResult = new JsonResult<List<Address>>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		List<Address> data = confirmService.getByUid(uid);
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}
	
	
	@GetMapping("showCarts")
	public JsonResult<List<CartVO>> showCarts(HttpSession session,Integer[] cids){
		JsonResult<List<CartVO>> jsonResult = new JsonResult<List<CartVO>>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		List<CartVO> data = confirmService.getByCids(uid, cids);
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}
}
