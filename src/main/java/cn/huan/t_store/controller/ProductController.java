package cn.huan.t_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.huan.t_store.entity.Product;
import cn.huan.t_store.service.ProductService;
import cn.huan.t_store.util.JsonResult;


@RestController
@RequestMapping("products")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@GetMapping("showNewArr")
	public JsonResult<List<Product>> showNewArr() {
		JsonResult<List<Product>> jsonResult = new JsonResult<List<Product>>();
		List<Product> data = productService.getNewArrival();
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}

	@GetMapping("showHotSort")
	public JsonResult<List<Product>> showHotSort() {
		List<Product> hotSort = productService.getHotSort();
		return new JsonResult<>(OK, hotSort);
	}

	@GetMapping("{id}/showOne")
	public JsonResult<Product> showOne(@PathVariable("id") Integer id) {
		JsonResult<Product> jsonResult = new JsonResult<Product>();
		Product data = productService.getById(id);
		jsonResult.setState(OK);
		jsonResult.setData(data);
		return jsonResult;
	}

	@GetMapping("showLikeList")
	public JsonResult<List<Product>> showLikeList(String keyName) {
		List<Product> likeList = productService.getLikeList(keyName);
		return new JsonResult<>(OK, likeList);
	}
}
