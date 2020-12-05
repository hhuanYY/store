package cn.huan.t_store.mapper;

import java.util.List;

import cn.huan.t_store.entity.Address;


/**
 * 收获地址结构
 * @author Administrator
 *
 */
public interface AddressMapper {
	
	/**
	 *   插入收货地址数据
	 * @param address 所有收获地址数据
	 * @return 受影响的行数
	 * INSERT INTO 
	 */
	Integer insert(Address address);

	/**
	 * 基于aid删除收货地址
	 * @param aid 
	 * @return 生效行数
	 */
	Integer deleteByAid(Integer aid);
	
	
	/**
	 * 统计某用户的收货地址的数量
	 * @param uid 用户的id
	 * @return 该用户收获地址的数量
	 * select count(*) from t_address where uid=?
	 */
	Integer countByUid(Integer uid);
	
	

	/**
	 * SELECT * FROM t_address WHERE uid=? ORDER BY is_default desc,created_time desc;
	 * @param uid
	 * @return 返回某个用户的所有收货地址 
	 */
	List<Address> findByUid(Integer uid);
	
	
	/**
	 * 根据aid查询收货地址
	 * @param aid 收货地址 id
	 * @return 查询到的某天收货地址
	 */
	Address findByAid(Integer aid);
	
	
	
	
	
	
	
	
	
	
	
	
}
