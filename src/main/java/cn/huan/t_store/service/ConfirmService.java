package cn.huan.t_store.service;

import java.util.List;

import cn.huan.t_store.entity.Address;
import cn.huan.t_store.entity.CartVO;



public interface ConfirmService {
	

	/**
	 * 查询该用户的所有收货地址 
	 * @param uid
	 * @return
	 */
	List<Address> getByUid(Integer uid);
	
	
	List<CartVO> getByCids(Integer uids,Integer[] cids);

}

