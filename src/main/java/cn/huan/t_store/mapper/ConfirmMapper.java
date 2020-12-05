package cn.huan.t_store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.huan.t_store.entity.Address;
import cn.huan.t_store.entity.CartVO;


public interface ConfirmMapper {

	/**
	 *    在订单页面显示收货地址
	 * SELECT * FROM t_address WHERE uid=?
	 * @return
	 */
	List<Address> findByUidAddress(Integer uid);
	
	
	/**
	 *    显示用户勾选的购物车商品,使用关联t_product和t_cart查询数据
	 *    查询该用户的部购物车商品，两个条件：1.指定用户 + 2.那几条商品
	 * SELECT * FROM from t_cart LEFT JOIN t_product ON t_cart.pid=t_product.id
	 * WHERE uid=? AND cid IN(?,?,?)
	 * 
	 * 多条数据的参数则用数组
	 * @return
	 */
	List<CartVO> findByCids(
			@Param("uid") Integer uid,
			@Param("cid") Integer[] cids);
	
}
