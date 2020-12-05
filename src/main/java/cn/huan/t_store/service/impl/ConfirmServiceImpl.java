package cn.huan.t_store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huan.t_store.entity.Address;
import cn.huan.t_store.entity.CartVO;
import cn.huan.t_store.mapper.ConfirmMapper;
import cn.huan.t_store.service.ConfirmService;
import cn.huan.t_store.service.ex.CartNotException;
import cn.huan.t_store.service.ex.UserNotMatchingException;



@Service
public class ConfirmServiceImpl implements ConfirmService{

	@Autowired
	private ConfirmMapper confirmMapper;

	@Override
	public List<Address> getByUid(Integer uid) {
		List<Address> result = findByUid(uid);
		for (Address address : result) {
			if (!address.getUid().equals(uid)) {
				throw new UserNotMatchingException("操作失败,无法操作他人数据...");
			}
			address.setUid(null); //因为在session中可以取到，所以可以设置为null
		}
		return result;
	}
	
	
	@Override
	public List<CartVO> getByCids(Integer uid,Integer[] cids) {
		//1.查询用户勾选的购物车商品
		List<CartVO> result = findByCids(uid, cids);
		//2.判断是否有查询的数据，若没有则表示出错，因为只有用户勾选才能显示，用户不勾选则不会到该页面
		if (result.size() == 0) {
			throw new CartNotException("操作失败,请放回上一层勾选商品...");
		}
		//3.
		return result;
	}
	
	
	private List<CartVO> findByCids(Integer uid,Integer[] cids){
		return confirmMapper.findByCids(uid, cids);
	}
	
	
	private List<Address> findByUid(Integer uid){
		return confirmMapper.findByUidAddress(uid);
	}
	
	
}
