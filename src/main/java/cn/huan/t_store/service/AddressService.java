package cn.huan.t_store.service;

import java.util.List;

import cn.huan.t_store.entity.Address;


/**
 *   收获地址的业务层接口
 * @author Administrator
 *
 */
public interface AddressService {

	/**
	 * 添加收获地址的抽象方法:
	 *  因为在添加成功后，除了给用户提示成功提示，不需要再给客户端任何数据，所以返回值类型为void
	 */
	void addnew(Integer uid,String username,Address address);
	
	
	/**
	 * 基于aid删除收货地址
	 * @param aid
	 */
	void setDeleteByAid(Integer aid,Integer uid);
	

	/**
	 * 通过uid查询到该用户的所有收货地址信息
	 * @param uid  用户id
	 * @return 返回该用户的所有收获地址
	 */
	List<Address> getByUid(Integer uid);
	
	
	/**
	 *   通过aid查询用户收货地址
	 * @param aid
	 * @return
	 */
	Address getByAid(Integer aid);
	
}
