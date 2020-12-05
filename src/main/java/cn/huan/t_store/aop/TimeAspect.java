package cn.huan.t_store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 给所有的业务层方法，测试一下执行每个业务需要花费的事件
 * @author Administrator
 *
 */
@Aspect
@Component
public class TimeAspect {

	@Around("execution(* cn.huan.t_store.service.impl.*.*(..))")	
	public Object TimeTest(ProceedingJoinPoint pjp) throws Throwable {

		
		//调用业务方法之前生成时间
		long start = System.currentTimeMillis();

		//相当于调用业务层的方法
		Object result = pjp.proceed();

		//调用业务方法之后生成时间
		long end = System.currentTimeMillis();

		//相减得到
		System.err.println("耗时:"+(end-start)+"ms");

		return result;
	}

}


