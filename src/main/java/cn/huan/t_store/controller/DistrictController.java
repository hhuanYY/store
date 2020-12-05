package cn.huan.t_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.huan.t_store.entity.District;
import cn.huan.t_store.service.DistrictService;
import cn.huan.t_store.util.JsonResult;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{

	@Autowired
	private DistrictService districtService;
	
	@GetMapping("show")
	public JsonResult<List<District>> getByParent(String parent){
		List<District> data = districtService.showDistricts(parent);
		JsonResult<List<District>> jsonResult = new JsonResult<List<District>>();
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}
}
