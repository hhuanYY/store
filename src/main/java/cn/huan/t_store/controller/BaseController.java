package cn.huan.t_store.controller;

/**
 *   用于将所有控制器的静态常量抽到基类中, 只用于被继承的类可以设置为抽象类
 * @author Administrator
 *
 */
abstract class BaseController {
	protected static final String OK="2000";
	
//  还可以将每次从session中取出uid和username的方法封装在基类中
//	protected String getUidSession(HttpSession session) {
//		return session.getAttribute("uid").toString();
//	}
}
