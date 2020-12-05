package cn.huan.t_store.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.huan.t_store.interceptor.LoginInterceptor;


@Configuration
public class InterceptorConfigur implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//向上造型，接口类型指向实现类的对象
		HandlerInterceptor interceptor = new LoginInterceptor();
		
		//在List集合中添加 白名单,表示不登录也可以访问的网址
		List<String> patterns = new ArrayList<String>();
		patterns.add("/users/reg");		
		patterns.add("/users/login");	
		patterns.add("/districts/**"); // 显示省市区设置为白名单 
		patterns.add("/products/**");
		patterns.add("/web/jquery-getUrlParam.js"); //将获取网址数据的JS文件添加白名单
		patterns.add("/web/index.html");
		patterns.add("/web/product.html");
		patterns.add("/web/register.html");
		patterns.add("/web/login.html");
		patterns.add("/bootstrap3/**");  //拦截器也会将静态资源拦截，所以需要设置为白名单
		patterns.add("/css/**");
		patterns.add("/js/**");
		patterns.add("/images/**");
		
		//添加拦截器                         
		registry.addInterceptor(interceptor) //设置黑名单的方法
		    .addPathPatterns("/**")
		    .excludePathPatterns(patterns); //设置白名单的方法
	}
}
