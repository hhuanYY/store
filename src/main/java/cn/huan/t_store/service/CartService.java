package cn.huan.t_store.service;

import java.util.List;

import cn.huan.t_store.entity.CartVO;


public interface CartService {

	/**
	 *    添加至购物车
	 */
	void addToCart(Integer uid,Integer pid,String username,Integer acount);
	
	
	/**
	 * 删除某购物车商品
	 * @param cid
	 * @param uid
	 */
	void getDeleteByCid(Integer cid,Integer uid);
	
	
	
	/**
	 * 关联查询，显示用户的购物车信息(两张表的内容)
	 * @param uid
	 * @return
	 */
	List<CartVO> getAllCarts(Integer uid);
	
	/**
	 * 购物车点击+号自增1
	 * @param cid
	 * @param uid
	 * @param username
	 * @return
	 */
	Integer addNum(Integer cid,Integer uid,String username);
	
	/**
	 * 购物车点击-号自减1
	 * @param cid
	 * @param uid
	 * @param username
	 * @return
	 */
	Integer reduceNum(Integer cid,Integer uid,String username);
	
}
