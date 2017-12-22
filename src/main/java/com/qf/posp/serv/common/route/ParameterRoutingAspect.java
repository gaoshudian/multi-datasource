package com.qf.posp.serv.common.route;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.qf.posp.serv.common.datasource.AbstractDataSourceManager;
import org.springframework.util.StringUtils;

/**
 * 名称: ParameterRoutingAspect.java <br>
 * 描述: 参数方式 AOP 处理自动路由.<br>
 * 类型: JAVA <br>
 * 最近修改时间:2017/7/12 17:27.<br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/7/12 17:27.
 */
@Aspect
@Order(0)// 保证该AOP在@Transactional之前执行
@Component
public class ParameterRoutingAspect {

	@Autowired
	AbstractDataSourceManager dataSourceManager;

	/**
	 * 定义切面
	 */
	@Pointcut("execution(* com.qf..*.*(com.qf.posp.serv.common.route.RoutingContext, ..))")
	public void declareJointPointExpression() {
	}

	@Before("declareJointPointExpression()")
	public void proceed(JoinPoint joinPoint) throws Throwable {
		//先保存原来的context
		String context = RoutingContextHolder.getContext();
		Object[] args = joinPoint.getArgs();
		RoutingContext routingContext = (RoutingContext)args[0];
		String tenantId = routingContext.getMerchantCode();
		Preconditions.checkNotNull(tenantId, "tenantId can't be null.");
		if (StringUtils.isEmpty(context) || !tenantId.equals(context)){
			dataSourceManager.setDynamicDataSource(tenantId);
		}
	}
}