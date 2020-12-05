package cn.huan.t_store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.huan.t_store.entity.Cart;
import cn.huan.t_store.entity.CartVO;


public interface CartMapper {

	/**
	 * 插入购物车列表
	 * INSERT INTO t_cart(字段名) values(实体类属性名)
	 * @return
	 */
	Integer insert(Cart cart);
	
	
	/**
	 * 基于cid删除某个商品
	 * DELETE FROM t_cart WHERE cid=?
	 * @return
	 */
	Integer deleteByCid(Integer cid);
	
	
	
	/**
	 * 当该商品已经添加至购物车，直接修改num数值即可
	 * @param cid
	 * @param price
	 * @param modified_user
	 * @param modified_time
	 * @return
	 */
	Integer update(			
			@Param("cid")Integer cid,
			@Param("num")Integer num,
			@Param("modified_user")String modified_user,
			@Param("modified_time")Date modified_time);
	

	/**
	 * 根据uid和Pid同时满足的条件下，查询即将加入购物车的商品是否已经被添加至购物车
	 * @param uid
	 * @param pid
	 * @return
	 */
	Cart findByUidAndPid(
			@Param("uid")Integer uid,
			@Param("pid")Integer pid);
	
	
	/** 关联查询多条数据 
	        SELECT 
	    	cid,uid,pid,t_cart.num,t_cart.price,title,image,t_product.price AS realPrice
	    FROM 
	    	t_cart LEFT JOIN t_product ON t_cart.uid = t_product.id 
	    WHERE 
	    	uid=? ORDER BY created_time DESC
	 * @param uid
	 * @return 返回多天查询结果 
	 */
	List<CartVO> findAllCarts(Integer uid);
	
	
	/**
	 * 根据cid查询数据是否存在
	 * @param cid
	 * @return
	 */
	Cart findByCid(Integer cid);
	

}
