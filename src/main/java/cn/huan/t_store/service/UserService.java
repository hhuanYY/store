package cn.huan.t_store.service;

import cn.huan.t_store.entity.User;

/**
 * 处理用户数据的业务层接口
 */
public interface UserService {

	/**
	 * 用户注册
	 * @param user 需要注册的用户信息
	 */
	void reg(User user);
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登录的用户数据
	 */
	User login(String username, String password);
	
	/**
	 * 修改密码
	 * @param uid 用户id
	 * @param username 用户名
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 */
	void changePassword(Integer uid, String username, String oldPassword, String newPassword);
	
	/**
	 * 显示用户个人资料
	 * @param uid 用户的id
	 * @return 匹配的用户的个人资料
	 */
	User showInfo(Integer uid);
	
	/**
	 * 修改用户的个人资料
	 * @param uid 用户的id
	 * @param username 用户名
	 * @param user 用户的新个人资料
	 */
	void changeInfo(Integer uid, String username, String phone,String email,Integer gender);
	
	/**
	 * 修改用户的头像
	 * @param uid 用户的id
	 * @param username 用户名
	 * @param avatar 用户的新头像路径
	 */
	void changeAvatar(Integer uid, String username, String avatar);
	
}






