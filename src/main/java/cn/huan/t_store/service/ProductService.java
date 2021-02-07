package cn.huan.t_store.service;

import java.util.List;

import cn.huan.t_store.entity.Product;


public interface ProductService {

	/**
	 * 基于商品上架的前提下，显示商品
	 * @return
	 */
	List<Product> getNewArrival();

	/**
	 * 热门排行
	 * @return
	 */
	List<Product> getHotSort();

	/**
	 *   根据id查询商品
	 * @param id
	 * @return
	 */
	Product getById(Integer id);

	/**
	 * 模糊查询商品
	 * @param keyName
	 * @return
	 */
	List<Product> getLikeList(String keyName);
	
}
