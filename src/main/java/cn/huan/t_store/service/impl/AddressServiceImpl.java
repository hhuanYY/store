package cn.huan.t_store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huan.t_store.entity.Address;
import cn.huan.t_store.mapper.AddressMapper;
import cn.huan.t_store.service.AddressService;
import cn.huan.t_store.service.DistrictService;
import cn.huan.t_store.service.ex.AddressCountException;
import cn.huan.t_store.service.ex.AddressNotFoundException;
import cn.huan.t_store.service.ex.DeleteException;
import cn.huan.t_store.service.ex.DeleteOutOfIndexException;
import cn.huan.t_store.service.ex.InsertException;


/**
 *    收获地址接口的实现类 ，  业务层的实现类，必须添加 @Service注解
 * @author Administrator
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired 
	private DistrictService districtService;
	
	
	@Value("${project.address-max-count}")
	private Integer addressMaxCount;
	
	@Override
	public void addnew(Integer uid,String username,Address address) {
		//1.先根据uid判断当前登录用户的收货地址条数,且需要uid，在控制器层中通过获取session中的uid即可
		Integer count = addressMapper.countByUid(uid);
	    //2.限制用户收货地址条数 ,  不大于15条 ， 超出则抛出异常
		if (count >= addressMaxCount) {
			throw new AddressCountException("添加收获地址失败,收获地址上限条数为："+addressMaxCount);
		}
		
		//3.补充数据完整性
		Date date = new Date();
		//
		address.setUid(uid); //自行提交 uid，因为在收货地址中用户提交的数据一定不包含uid
		//  ---自行提交省市区的名称 --
		//向数据库中添加省 --  调用districtService.findNameByCode() 需要先自动装配
		
		/**
		 * 此时能够获取到省市区的代号是因为,使用的是 .serialize()方法将用户输入的各种信息
		 * 传到控制器，再到业务层，且使用serialize方法的 <select>节点必须添加 name属性和value属性进行拼接后
		 * 传到Address中  
		 */
		String provinceCode = address.getProvinceCode(); 
		String provinceName = districtService.findNameByCode(provinceCode);
		address.setProvinceName(provinceName);
		//向数据库中添加市
		address.setCityName(districtService.findNameByCode(address.getCityCode()));
		//向数据库中添加区
		address.setAreaName(districtService.findNameByCode(address.getAreaCode()));
		
		// 根据查询到的count来处理
		Integer isDefault = count == 0 ? 1:0;
		address.setIsDefault(isDefault);
		//补充日志数据
		address.setCreatedUser(username); //通过控制器中的session获取到
		address.setCreatedTime(date);
		address.setModifiedUser(username);
		address.setModifiedTime(date);
		
		//3.调用持久层的 insert方法插入收货地址
		Integer row = addressMapper.insert(address);
		if (row != 1) {
			throw new InsertException("添加收获地址失败,请联系系统管理员...");
		}
	}
	
	
	
	/**
	 * 显示用户收货地址
	 */
	@Override
	public List<Address> getByUid(Integer uid) {
		//1.通过uid查询到该用户的说有收获地址
		List<Address>addresses = addressMapper.findByUid(uid);
		for (Address address : addresses) {
			//将不需要显示给页面的属性设置为null,例如:uid(在session中可以获取,可不显示),省市区代号,是否置顶,四个日志
			address.setUid(null);
			address.setProvinceCode(null);
			address.setCityCode(null);
			address.setAreaCode(null);
			address.setIsDefault(null);
			address.setCreatedUser(null);
			address.setCreatedTime(null);
			address.setModifiedTime(null);
			address.setModifiedUser(null);
		}
		return addresses;
	}
	
	
	@Override
	@Transactional
	public void setDeleteByAid(Integer aid,Integer uid) {
		//1.先查询需要删除的地址是否存在,不存在抛出异常,调用私有方法即可
		Address result = findByAid(aid);
		if (result == null) {
			throw new AddressNotFoundException("删除失败,该收货地址不存在...");
		}
		
		//2.排除当前用户操作其他用户的数据(例如：当前用户注销并登陆其他用户，但是删除收货地址页面未刷新，导致出错)
		//   即取出查询到该收货地址所属的用户，并和登录时存入session中的用户比较即可
		if (!result.getUid().equals(uid)) {
			throw new DeleteOutOfIndexException("删除失败,请规范操作--不允许操作其他用户数据...");
		}
		//3.执行删除,判断生效行数是否不为1,则抛出异常
		deleteByAid(aid);
	}
	
	
	@Override
	public Address getByAid(Integer aid) {
		Address result = findByAid(aid);
		if (result == null) {
			throw new AddressNotFoundException("操作失败,收货地址不存在...");
		}
		result.setProvinceCode(null);
		result.setCityCode(null);
		result.setAreaCode(null);
		result.setIsDefault(null);
		result.setCreatedUser(null);
		result.setCreatedTime(null);
		result.setModifiedTime(null);
		result.setModifiedUser(null);
		return result;
	}

	
	
	/**
	 * 调用持久层抽象方法的私有方法
	 * @param aid
	 * @return
	 */
	private Address findByAid(Integer aid) {
		return addressMapper.findByAid(aid);
	}
	
	/**
	 * 调用持久层删除收货地址的私有方法 
	 * @param aid
	 */
	private void deleteByAid(Integer aid) {
		Integer row = addressMapper.deleteByAid(aid);
		if (row != 1) { //抛出异常
			throw new DeleteException("删除失败，请联系系统管理员...");
		}
	}




	
}
