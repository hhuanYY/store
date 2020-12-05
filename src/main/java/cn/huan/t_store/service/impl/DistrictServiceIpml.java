package cn.huan.t_store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huan.t_store.entity.District;
import cn.huan.t_store.mapper.DistrictMapper;
import cn.huan.t_store.service.DistrictService;


@Service
public class DistrictServiceIpml implements DistrictService{

	@Autowired
	private DistrictMapper districtMapper;
	
	
	@Override
	public String findNameByCode(String code) {
		String name = districtMapper.findNameByCode(code);
		return name;
	}
	
	@Override
	public List<District> showDistricts(String parent) {
		//1.基于parent查询对应的省市区名称,并返回对应的结果
		List<District> list = districtMapper.findByParent(parent);
		//2.遍历集合，并将不需要用到的id和parent设置为null
		for (District district : list) {
			district.setId(null);
			district.setParent(null); //因为用户已知parent的编号，所以不需要，且为了更好的在页面中不显示为null的数据
		}
		return list;
	}



}
