package cn.huan.t_store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huan.t_store.entity.User;
import cn.huan.t_store.mapper.UserMapper;
import cn.huan.t_store.service.UserService;
import cn.huan.t_store.service.ex.InsertException;
import cn.huan.t_store.service.ex.PasswordNotMatchException;
import cn.huan.t_store.service.ex.UpdateException;
import cn.huan.t_store.service.ex.UserNotFoundException;
import cn.huan.t_store.service.ex.UsernameDuplicateException;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void reg(User user) {
		// 日志
		System.err.println("UserServiceImpl.reg()");
		System.err.println("\t注册用户：" + user);
		// 从参数对象user中取出用户名username
		String username = user.getUsername();
		// 根据username调用持久层的findByUsername()方法查询用户数据
		User result = userMapper.findByUsername(username);
		// 判断查询结果是否不为null
		if (result != null) {
			// 是：UsernameDuplicationException
			throw new UsernameDuplicateException("注册失败！用户名已经被占用！");
		}

		// 用户名没有被占用，则调用addnew()插入用户数据
		// 补全数据：加密过后的密码，盐值
		String salt = UUID.randomUUID().toString();
		String md5Password = getMd5Password(user.getPassword(), salt);
		user.setPassword(md5Password);
		user.setSalt(salt);
		// 补全数据：isDelete
		user.setIsDelete(0);
		// 补全数据：4个日志
		Date now = new Date();
		user.setCreatedUser(username);
		user.setCreatedTime(now);
		user.setModifiedUser(username);
		user.setModifiedTime(now);
		// 执行插入数据
		System.err.println("\t执行注册：" + user);
		Integer rows = userMapper.addnew(user);
		// 判断结果
		if (rows != 1) {
			throw new InsertException("注册失败！插入用户数据时出现未知错误，请联系系统管理员！");
		}
	}

	@Override
	public User login(String username, String password) {
		// 输出日志
		System.err.println("UserServiceImpl.login()");
		System.err.println("\tusername：" + username);
		System.err.println("\tpassword(原始密码)：" + password);
		// 基本参数username调用持久层的findByUsername()方法，根据用户名查询用户数据
		User result = userMapper.findByUsername(username);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：用户名不存在，抛出异常UserNotFoundException
			throw new UserNotFoundException("登录失败！用户名不存在！");
		}

		// 如果执行到此处，则表示查询结果不为null，则判断isDelete是否不为0
		if (result.getIsDelete() != 0) {
			// 是：已删除，抛出异常UserNotFoundException
			throw new UserNotFoundException("登录失败！用户数据已删除！");
		}

		// 如果执行到此处，则表示用户数据存在且正常，则从查询结果中取出盐值
		String salt = result.getSalt();
		// 将参数password结合以上取出的盐值执行加密
		String md5Password = getMd5Password(password, salt);
		// 将查询结果中的password与以上加密得到的密文进行对比，判断对比结果是否不一致
		System.err.println("\t数据库中的密码：" + result.getPassword());
		if (!md5Password.equals(result.getPassword())) {
			// 是：密码错误，抛出异常PasswordNotMatchException
			throw new PasswordNotMatchException("登录失败！密码错误！");
		}

		// 如果执行到此处，则表示用户数据存在且正常，密码也正常，视为登录成功，则新创建User对象
		User user = new User();
		// 将查询结果中的uid, username, avatar封装到新User对象中
		user.setUid(result.getUid());
		user.setUsername(result.getUsername());
		user.setAvatar(result.getAvatar());
		System.err.println("\t返回：" + user);
		// 返回新User对象
		return user;
	}

	@Override
	public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
		// 输出日志
		System.err.println("UserServiceImpl.changePassword()");
		System.err.println("\t原密码：" + oldPassword);
		System.err.println("\t新密码：" + newPassword);
		// 基于参数uid调用持久层的findByUid()查询用户数据
		User result = userMapper.findByUid(uid);
		System.err.println("\t根据uid查询到的结果：" + result);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("修改密码失败！尝试访问的用户数据不存在！");
		}

		// 判断查询结果中的isDelete是否不为0
		if (result.getIsDelete() != 0) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("修改密码失败！用户数据已删除！");
		}

		// ===== 检查原密码 =====
		System.err.println("\t===== 检查原密码 =====");
		// 从查询结果中取出盐值
		String salt = result.getSalt();
		// 基于参数oldPassword和盐值执行加密，得到oldMd5Password
		String oldMd5Password = getMd5Password(oldPassword, salt);
		// 判断oldMd5Password与查询结果中的password是否不一致
		System.err.println("\t数据库中的密码：" + result.getPassword());
		if (!oldMd5Password.equals(result.getPassword())) {
			// 是：抛出PasswordNotMatchException
			throw new PasswordNotMatchException("修改密码失败！原密码错误！");
		}

		// ===== 更新新密码 =====
		System.err.println("\t===== 更新新密码 =====");
		// 基于参数newPassword和盐值执行加密，得到newMd5Password
		String newMd5Password = getMd5Password(newPassword, salt);
		// 调用持久层的updatePasswordByUid()更新密码，获取返回值
		Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
		// 判断返回值(受影响行的行数)是否不为1
		if (rows != 1) {
			// 是：抛出UpdateException
			throw new UpdateException("修改密码失败！更新用户密码时出现未知错误，请联系系统管理员！");
		}
	}

	@Override
	public User showInfo(Integer uid) {
		// 基于参数uid调用持久层的findByUid()方法查询用户数据
		User result = userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("获取用户数据失败！尝试访问的用户数据不存在！");
		}

		// 判断查询结果中的isDelete是否不为0
		if (result.getIsDelete() != 0) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("获取用户数据失败！用户数据已删除！");
		}

		// 创建新的User对象
		User user = new User();
		// 通过查询结果，向新User对象中封装：username, phone, email, gender
		user.setUsername(result.getUsername());
		user.setPhone(result.getPhone());
		user.setEmail(result.getEmail());
		user.setGender(result.getGender());
		// 返回新User对象
		return user;
	}

	@Override
	public void changeInfo(Integer uid, String username, String phone,String email,Integer gender) {
		// 基于参数uid调用持久层的findByUid()方法查询用户数据
		User result = userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("修改用户数据失败！尝试访问的用户数据不存在！");
		}

		// 判断查询结果中的isDelete是否不为0
		if (result.getIsDelete() != 0) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("修改用户数据失败！用户数据已删除！");
		}

		User user = new User();
		// 向参数user中补全：uid > 参数uid
		user.setUid(uid);
		// 向参数user中补全：modifiedUser > 参数username
		user.setModifiedUser(username);
		// 向参数user中补全：modifiedTime > new Date()
		user.setModifiedTime(new Date());
		user.setPhone(phone);
		user.setEmail(email);
		user.setGender(gender);
		// 基于参数user调用持久层的updateInfoByUid()执行修改，获取返回值
		Integer rows = userMapper.updateInfoByUid(user);
		// 判断返回值(受影响行的行数)是否不为1
		if (rows != 1) {
			// 是：抛出UpdateException
			throw new UpdateException("修改用户数据失败！更新用户数据时出现未知错误，请联系系统管理员！");
		}
	}

	@Override
	public void changeAvatar(Integer uid, String username, String avatar) {
		// 基于参数uid调用持久层的findByUid()方法查询用户数据
		User result = userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("修改用户头像失败！尝试访问的用户数据不存在！");
		}

		// 判断查询结果中的isDelete是否不为0
		if (result.getIsDelete() != 0) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("修改用户头像失败！用户数据已删除！");
		}

		// 调用持久层的updateAvatarByUid()执行更新，获取返回值
		Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
		// 判断返回值(受影响行的行数)是否不为1
		if (rows != 1) {
			// 是：抛出UpdateException
			throw new UpdateException("修改用户头像失败！更新用户头像时出现未知错误，请联系系统管理员！");
		}
	}

	/**
	 * 执行加密
	 * 
	 * @param password 原始密码
	 * @param salt     盐值
	 * @return 加密后的密码
	 */
	private String getMd5Password(String password, String salt) {
		// 加密规则：
		// 1. 使用salt + password + salt作为原文
		// 2. 循环加密3次
		System.err.println("\t执行加密：");
		System.err.println("\t原文：" + password);
		System.err.println("\t盐值：" + salt);
		String md5Password = salt + password + salt;
		for (int i = 0; i < 3; i++) {
			md5Password = DigestUtils.md5Hex(md5Password);
		}
		System.err.println("\t密文：" + md5Password);
		return md5Password;
	}

}
