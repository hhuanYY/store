package cn.huan.t_store.service;

import cn.huan.t_store.entity.Order;

public interface OrderService {

	/**
	 *    插入订单数据
	 *    1. 除了响应插入成功外，还需要个给用户显示订单号、金额等
	 *    2.返回值类型写Order即可
	 */
	
	Order createOrder(Integer uid,Integer aid,Integer[] cids,String username);

}
