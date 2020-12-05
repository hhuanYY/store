package cn.huan.t_store.mapper;

import java.util.List;

import cn.huan.t_store.entity.Product;


public interface ProductMapper {

	/**
	 * 基于商品上架的前提下，显示商品
	 * SELECT * FROM t_product　WHERE status=1 ORDER BY created_time desc LIMIT 0，4；
	 * @return
	 */
	List<Product> findNewArrival();
	
	
	/**
	 *    根据商品id查询商品
	 * @param id
	 * @return
	 */
	Product findById(Integer id);
}
