package cn.huan.t_store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huan.t_store.entity.Product;
import cn.huan.t_store.mapper.ProductMapper;
import cn.huan.t_store.service.ProductService;
import cn.huan.t_store.service.ex.ProductNotFoundException;



@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductMapper productMapper;
	
	//显示商品
	@Override
	public List<Product> getNewArrival() {
		List<Product> result = findNewArrvial();
		for (Product product : result) {
			product.setStatus(null);
			product.setPriority(null);
			product.setCreatedUser(null);
			product.setCreatedTime(null);
			product.setModifiedUser(null);
			product.setModifiedTime(null);
		}
		return result;
	}


	/**
	 * 热门排行
	 * @return
	 */
	@Override
	public List<Product> getHotSort() {
		List<Product> hotSort = productMapper.findHotSort();
		return hotSort;
	}


	//根据id查询商品
	@Override
	public Product getById(Integer id) {
		Product result = findById(id);
		if (result == null) {
			throw new ProductNotFoundException("显示错误,该商品不存在...");
		}
		result.setStatus(null);
		result.setPriority(null);
		result.setCreatedUser(null);
		result.setCreatedTime(null);
		result.setModifiedUser(null);
		result.setModifiedTime(null);
		return result;
	}

	@Override
	public List<Product> getLikeList(String keyName) {
		List<Product> likeList = productMapper.findLikeList(keyName);
		return likeList;
	}

	/**
	 * 持久层的查询直接return
	 * @return
	 */
	private List<Product> findNewArrvial(){
		return productMapper.findNewArrival();
	}
	
	
	/**
	 * 根据id查询商品的私有方法
	 * @param id
	 * @return
	 */
	private Product findById(Integer id) {
		return productMapper.findById(id);
	}

}
