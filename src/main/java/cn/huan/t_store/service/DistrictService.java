package cn.huan.t_store.service;

import java.util.List;

import cn.huan.t_store.entity.District;


public interface DistrictService {

	/**
	 *   显示省市区列表
	 * @param parent 父级编号， 86为输出所有省的编号
	 * @return 返回查询结果
	 */
	List<District> showDistricts(String parent);
	
	//根据代号查名称
	String findNameByCode(String code);
}
