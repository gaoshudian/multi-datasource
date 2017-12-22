package com.qf.posp.serv.common.route;

import com.qf.posp.serv.common.datasource.AbstractDataSourceManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 名称: RoutingContextHolder.java <br>
 * 描述: 存储每个线程使用的线程池标识，提供获取、修改和清除方法.<br>
 * 类型: JAVA <br>
 * 最近修改时间:2017/6/7 19:27.<br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/7 19:27.
 */
@Component
public class RoutingContextHolder implements ApplicationContextAware {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	private static AbstractDataSourceManager dataSourceManager;

	public static String getContext() {
		return contextHolder.get();
	}

	/**
	 * 在spring未初始化完成时，不能调用此方法
	 * @param tenantId
	 */
	public static void setContext(String tenantId) {
		dataSourceManager.setContextDataSource(tenantId);
		contextHolder.set(tenantId);
	}

	public static void remove() {
		contextHolder.remove();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		dataSourceManager = applicationContext.getBean(AbstractDataSourceManager.class);
	}
}