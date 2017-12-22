package com.qf.posp.serv.common.route;

import com.google.common.base.Preconditions;
import com.qf.posp.serv.common.base.SpelExpressionParserSingleton;
import com.qf.posp.serv.common.base.SpringParamNameDiscovererSingleton;
import com.qf.posp.serv.common.datasource.AbstractDataSourceManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 名称: AnnotationRoutingAspect.java <br>
 * 描述: 注解方式 AOP 处理自动路由.<br>
 * 类型: JAVA <br>
 * 最近修改时间:2017/6/30 19:27.<br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/30 19:27.
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class AnnotationRoutingAspect {

	@Autowired
	AbstractDataSourceManager dataSourceManager;

	@Before("@annotation(routingDatasource)")
	public void proceed(JoinPoint joinPoint, RoutingDatasource routingDatasource)
			throws Throwable {
		//先保存原来的context
		String context = RoutingContextHolder.getContext();
		String tenantId = parseKey(routingDatasource.tenantId(), joinPoint);
		Preconditions.checkNotNull(tenantId, "tenantId can't be null.");
		if (StringUtils.isEmpty(context) || !tenantId.equals(context)){
			dataSourceManager.setDynamicDataSource(tenantId);
		}
	}

	/**
	 * 获取切点的key值
	 * @param key 定义在注解上，支持SPEL表达式
	 * @param joinPoint 切点对象
	 * @return
	 */
	private String parseKey(String key, JoinPoint joinPoint){
		Object [] args = joinPoint.getArgs();

		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method argsMethod = methodSignature.getMethod();
		Class [] argTypes = argsMethod.getParameterTypes();

		/**
		 *  获取被拦截方法对象
		 *  MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
		 *	而缓存的注解在实现类的方法上 所以应该使用反射获取当前对象的方法对象
		 */
		Method method = null;
		try {
			method = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName(), argTypes);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		//获取被拦截方法参数名列表(使用Spring支持类库)
		LocalVariableTableParameterNameDiscoverer
				discoverer = SpringParamNameDiscovererSingleton.INSTANCE.getInstance();
		String [] paraNames = discoverer.getParameterNames(method);

		//使用SPEL进行key的解析
		ExpressionParser parser = SpelExpressionParserSingleton.INSTANCE.getInstance();
		//SPEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		//把方法参数放入SPEL上下文中
		for (int i = 0; i < paraNames.length; i++) {
			context.setVariable(paraNames[i], args[i]);
		}
		return parser.parseExpression(key).getValue(context, String.class);
	}
}
