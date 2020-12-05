package cn.huan.t_store.mapper;

import java.util.List;

import cn.huan.t_store.entity.District;


/**
 *   显示省市区的持久层接口
 * @author Administrator
 *
 */

public interface DistrictMapper {
	/**
	 *    根据parent查询省对应的市，市对应的区
	 * @param parent 父级到位的行政代号，如果查询全国省，使用parent='86'作为父级单位的行政代号 
	 * @return 全国所有省，或者说有市，或者某市所有区的列表
	 */
	List<District> findByParent(String parent);
	
	
	/**
	 * select name t_dict_district where code=?
	 * @return
	 */
	String findNameByCode(String code);
	
}
