package cn.huan.t_store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.huan.t_store.entity.Address;
import cn.huan.t_store.service.AddressService;
import cn.huan.t_store.util.JsonResult;



@RestController
@RequestMapping("address")
public class AddressController extends BaseController{
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping("addnew")
	public JsonResult<Void> addnew(Address address,HttpSession session){
		JsonResult<Void> jsonResult = new JsonResult<Void>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		addressService.addnew(uid, username, address);
		jsonResult.setState(OK);
		return jsonResult;
	}
	
	
	@GetMapping("showAddress")
	public JsonResult<List<Address>> showAddress(HttpSession session){
		JsonResult<List<Address>> jsonResult = new JsonResult<List<Address>>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		List<Address> data = addressService.getByUid(uid);
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}

	
	@RequestMapping("{aid}/delete")
	public JsonResult<Void> deleteByAid(@PathVariable("aid") Integer aid,HttpSession session){
		JsonResult<Void> jsonResult = new JsonResult<Void>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		addressService.setDeleteByAid(aid, uid);
		jsonResult.setState(OK);
		return jsonResult;
	}
	
	
	
	
}
