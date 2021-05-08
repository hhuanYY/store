package cn.huan.t_store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.huan.t_store.entity.Log;
import cn.huan.t_store.service.LogService;
import cn.huan.t_store.service.impl.LogServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Date;

/**
 * @author admin
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private LogServiceImpl logServiceImpl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getSession().getAttribute("uid") == null) {
			response.sendRedirect(request.getContextPath() + "/web/login.html");
			return false;
		}
		return true;
	}


	/**
	 * 日志记录
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HttpSession session = request.getSession();
		Log log = new Log();
		String username = session.getAttribute("username").toString();
		String url = request.getHeader("referer");
		log.setUsername(username);
		log.setUrl(url);
		log.setTimes(new Date());
		try {
			if (logServiceImpl == null) {
				BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
				logServiceImpl = (LogServiceImpl) factory.getBean("logServiceImpl");

			}
			logServiceImpl.insertLog(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
