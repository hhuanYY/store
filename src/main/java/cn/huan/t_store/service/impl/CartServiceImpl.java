package cn.huan.t_store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huan.t_store.entity.Cart;
import cn.huan.t_store.entity.CartVO;
import cn.huan.t_store.entity.Product;
import cn.huan.t_store.mapper.CartMapper;
import cn.huan.t_store.service.CartService;
import cn.huan.t_store.service.ProductService;
import cn.huan.t_store.service.ex.CartNotException;
import cn.huan.t_store.service.ex.CartOutOfIndexException;
import cn.huan.t_store.service.ex.DeleteException;
import cn.huan.t_store.service.ex.InsertException;
import cn.huan.t_store.service.ex.UpdateException;
import cn.huan.t_store.service.ex.UserNotMatchingException;


@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public void addToCart(Integer uid,Integer pid,String username,Integer acount) {
		//1.先查询该商品是否已经加入购物车:
		Cart result = findByUidAndPid(uid, pid);
		if (result == null) {
			//2.若查询结果为null时，表示购物车中没有，则执行添加
			//补全数据
			Cart cart = new Cart();
			cart.setUid(uid);
			cart.setPid(pid);
			cart.setNum(acount);  //此时添加该商品的数量acount
			//通过商品编号查询到商品信息的价格
			System.err.println("开始...");
			Product product = productService.getById(pid);
			System.err.println("\tproduct:"+product);
			cart.setPrice(product.getPrice()); //价格从商品数据表中查询即可
			cart.setCreatedUser(username);
			cart.setCreatedTime(new Date());
			cart.setModifiedUser(username);
			cart.setModifiedTime(new Date());
			insertCart(cart);
			System.err.println("\tcart"+cart);
		}else {
			//3.如果查询结果不为null，表示该商品已经被加入购物车，则修改num数值即可
			//注意：此时这个num值：已添加至购物车时保存的num+此时还需要添加的num值
			Integer num = result.getNum() + acount;
			Integer cid = result.getCid();
			updateNum(cid, num, username, new Date());
		}
		

	}
	
	
	/**
	 * 显示购物车信息
	 */
	@Override
	public List<CartVO> getAllCarts(Integer uid) {
		List<CartVO> result = findAllCarts(uid);
		//遍历集合
//		for (CartVO cartVO : result) {
//			//将不需要响应给客户端的字段设置为null
//			//cartVO.setUid(null);
//		}
		return result;
	}
	
	
	//购物车自增1 
	@Override
	public Integer addNum(Integer cid,Integer uid,String username) {
		Cart result = findByCid(cid);
		//1.判断查询结果是否为null，则抛出异常 
		if (result == null) {
			throw new CartNotException("增加数量失败,该商品不存在...");
		}
		
		//2.判断是不是当前用户,从查询结果中的uid和存入session中的uid进行比较
		if (!result.getUid().equals(uid)) { //如果不等，则抛出异常
			throw new UserNotMatchingException("操作失败,不允许操作他人数据...");
		}
		
		//2.至此，该结果存在，执行修改数量即可
		//  从查询结果中拿到该商品原数量并 +1 ,并将得到的新数值作为将要修改的值传入
		Integer num = result.getNum()+1;
		updateNum(cid, num, username, new Date());
		
		return num;
	}

	//购物车自减1
	@Override
	public Integer reduceNum(Integer cid,Integer uid,String username) {
		//1.先根据cid查询结果是否存在
		Cart result = findByCid(cid);
		if (result == null) {
			throw new CartNotException("操作失败,该商品不存在...");
		}
		//2.判断当前操作的数据是否是当前用户
		if (!result.getUid().equals(uid)) {
			throw new UserNotMatchingException("操作失败,不允许操作他人数据...");
		}
		//3.执行自减一
		Integer num = result.getNum()-1; //将原数量取出并减1,得到新的数量并作为修改条件
		
		if (num < 1) {
			throw new CartOutOfIndexException("减少商品数量失败,商品数量必须大于1...");
		}
		
		updateNum(cid, num, username, new Date());
		
		return num;
	}

	
	//删除某商品购物车商品信息
	@Override
	public void getDeleteByCid(Integer cid,Integer uid) {
		//1.删除之前先查询基于cid删除的商品是否存在
		Cart result = findByCid(cid);
		if (result == null) {
			throw new CartNotException("操作失败,该商品不存在...");
		}
		//2.判断删当前操作的用户是否是同一个用户
		if (!result.getUid().equals(uid)) {
			throw new UserNotMatchingException("操作失败,不允许操作他人数据...");
		}
		//3.执行删除
		deleteByCid(cid);
	}
	
	
	
	/**
	 * 私有方法：删除某商品购物车商品信息
	 * @param cid
	 */
	private void deleteByCid(Integer cid) {
		Integer row = cartMapper.deleteByCid(cid);
		if (row != 1) {
			throw new DeleteException("删除失败...");
		}
	}
	
	/**
	 * 私有方法： 调用持久层
	 * @param cid
	 * @return
	 */
	private Cart findByCid(Integer cid) {
		return cartMapper.findByCid(cid);
	}

	/**
	 * 调用持久层查询的私有方法
	 * @param uid
	 * @return
	 */
	private List<CartVO> findAllCarts(Integer uid){
		return cartMapper.findAllCarts(uid);
	}
	
	/**
	 *    基于uid和pid查询商品的私有方法
	 * @param uid
	 * @param pid
	 * @return
	 */
	private Cart findByUidAndPid(Integer uid,Integer pid) {
		return cartMapper.findByUidAndPid(uid, pid);
	}
	
	/**
	 * 当查询结果为null时，表示该商品从未添加过，则添加至购物车
	 * @param cart
	 */
	private void insertCart(Cart cart) {
		Integer row = cartMapper.insert(cart);
		if (row != 1) {
			throw new InsertException("系统错误，请联系系统管理员...");
		}
	}
	
	/**
	 *   修改用户的num值
	 * @param cid
	 * @param num
	 * @param modified_user
	 * @param modified_time
	 */
	private void updateNum(Integer cid,Integer num,String modified_user,Date modified_time) {
		Integer row = cartMapper.update(cid, num, modified_user, modified_time);
		if (row != 1) {
			throw new UpdateException("系统错误，请联系系统管理员...");
		}
	}


}
