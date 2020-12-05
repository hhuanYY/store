package cn.huan.t_store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huan.t_store.entity.Address;
import cn.huan.t_store.entity.CartVO;
import cn.huan.t_store.entity.Order;
import cn.huan.t_store.entity.OrderItem;
import cn.huan.t_store.mapper.OrderMapper;
import cn.huan.t_store.service.AddressService;
import cn.huan.t_store.service.ConfirmService;
import cn.huan.t_store.service.OrderService;
import cn.huan.t_store.service.ex.InsertException;
import cn.huan.t_store.service.ex.UserNotMatchingException;



@Service
public class OrderServiceImpl implements OrderService{

	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ConfirmService confirmService;
	
	@Override
	public Order createOrder(Integer uid,Integer aid,Integer[] cids,String username) {
		//1.new一个order对象
		Order order = new Order();
		//2.补全数据
        //uid从session中获取即可		 
		order.setUid(uid);
		//创建订单时的收货地址各字段, 通过aid查询收货地址(额外写即可) 
		//在此处调用AddressService的抽象方法，而不是直接调用别人的持久层方法 
		Address result = addressService.getByAid(aid); //已经判断过,即可不必再次判断
		//判断收货地址是否归属当前用户
		if (!result.getUid().equals(uid)) {
			throw new UserNotMatchingException("操作失败,不可用其他用户收货地址...");
		}
		order.setRecvName(result.getName());
		order.setRecvPhone(result.getPhone());
		order.setRecvProvince(result.getProvinceName());
		order.setRecvCity(result.getCityName());
		order.setRecvArea(result.getAreaName());
		order.setRecvAddress(result.getAddress());
		
		//3.补全用户勾选商品的总金额：pay_amount
		//通过调用其他类的getBycarts方法,基于uid和aid查询用户勾选的所有商品的信息,即可得到每个商品的单价和数量
		List<CartVO> carts = confirmService.getByCids(uid, cids); //得到用户勾选的所有商品
		//遍历查询到的结果carts
		long allPrice = 0L;
		for (CartVO cartVO : carts) {
			//计算每个购物车商品的总价,最后求和
			long onePrice = cartVO.getPrice()*cartVO.getNum();
			allPrice += onePrice;
		}
		//将所有商品的总价设置到order对象的pay_amount中
		order.setPayAmount(allPrice);
		//订单状态：0-未支付，1-已支付，2-已取消，3-已关闭',
		order.setStatus(0);
		Date data = new Date();
		order.setOrderTime(data);
		//TODO 未设置
		order.setPayTime(null);
		//4个日志信息
		order.setCreatedUser(username);
		order.setCreatedTime(data);
		order.setModifiedUser(username);
		order.setModifiedTime(data);
		//4.调用订单插入私有方法,需要order,new一个即可
		insertOrder(order);
		
		
		//1.调用订单商品插入,因为有多个商品所有遍历.遍历上述的carts即可
		for (CartVO cartVO : carts) {
			//创建orderItem对象
			OrderItem orderItem = new OrderItem();
			//补全数据
			//订单号oid：因为在持久层时添加的属性，所以在实体类可直接获取
			orderItem.setOid(order.getOid());
			orderItem.setPid(cartVO.getPid());
			//补全每个商品的详细信息
			orderItem.setTitle(cartVO.getTitle());
			orderItem.setImage(cartVO.getImage());
			orderItem.setPrice(cartVO.getPrice());
			orderItem.setNum(cartVO.getNum());
			//total_price是一个商品的总价
			orderItem.setTotalPrice(cartVO.getPrice()*cartVO.getNum());
			orderItem.setCreatedUser(username);
			orderItem.setCreatedTime(data);
			orderItem.setModifiedUser(username);
			orderItem.setModifiedTime(data);
			//将填充好的orderItem对象放入插入方法中即可,都需要执行一次插入订单商品方法
			insertOrderItem(orderItem);
		}

		//最后将设置好的order对象返回
		return order;
		

	}
	
	
	//1.私有方法：调用持久层的订单插入
	private void  insertOrder(Order order) {
		Integer row = orderMapper.insertOrder(order);
		if (row != 1) {
			throw new InsertException("操作失败,请联系系统管理员...");
		}
	}
	
	//2.私有方法：调用持久层的订单商品插入
	private void  insertOrderItem(OrderItem orderItem) {
		Integer row = orderMapper.insertOrderItem(orderItem);
		if (row != 1) {
			throw new InsertException("操作失败,请联系系统管理员...");
		}
	}

	
}
