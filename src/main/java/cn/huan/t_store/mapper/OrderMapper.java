package cn.huan.t_store.mapper;

import cn.huan.t_store.entity.Order;
import cn.huan.t_store.entity.OrderItem;

public interface OrderMapper {

	/**
	 *   增删改的抽象方法的返回值类型为: Integer
	 *   修改：能够装入即可
	 *         1.一条完整数据可用对应的实体类
	 *         2.多天数据可用 List<对应实体类>
	 */
	
	//订单插入
	Integer insertOrder(Order order);
	
	//订单商品插入
	Integer insertOrderItem(OrderItem orderItem);
}
