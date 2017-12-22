package com.qf.posp.serv.common.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.qf.posp.serv.common.route.RoutingContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 名称: AbstractDataSourceManager.java <br>
 * 描述: 抽象的DataSourceManager，由客户端实现其中的两个方法initDataSources和getDataSources<br>
 * 时间: 2017/6/30 17:05 <br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/30 17:05
 */
@EnableConfigurationProperties({DataSourceConfigProperties.class})
public abstract class AbstractDataSourceManager implements ApplicationContextAware {

	@Autowired
	private DataSourceConfigProperties dataSourceConfigProperties;

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 项目启动时初始化的数据源列表.
	 * @return Map<key, value>, key 代表 tenantId, value 代表对应的DataSourceInfo.
	 */
	public abstract Map<String, DataSourceInfo> initDataSources();

	/**
	 * 通过tenantId获得对应的DataSourceInfo.
	 * @param tenantId 指定tenantId.
	 * @return 返回对应的DataSourceInfo.
	 */
	public abstract DataSourceInfo getDataSources(String tenantId);

	/**
	 * 通过tenantId设置对应的DynamicDataSource，如果不存在则创建一个tenantId对应的DynamicDataSource.
	 * @param tenantId 指定tenantId.
	 */
	public void setDynamicDataSource(String tenantId){
		RoutingContextHolder.setContext(tenantId);
	}

	public void setContextDataSource(String tenantId){
		DynamicDataSource dynamicDataSource = applicationContext.getBean(DynamicDataSource.class);
		if (!dynamicDataSource.isExist(tenantId)) {
			DataSourceInfo info =  getDataSources(tenantId);

			ConfigurableApplicationContext configurableApplicationContext
					= (ConfigurableApplicationContext) applicationContext;
			DefaultListableBeanFactory beanFactory
					= (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
			if (!beanFactory.containsBean(tenantId)) {
				BeanDefinitionBuilder beanDefinitionBuilder= BeanDefinitionBuilder
						.rootBeanDefinition(com.alibaba.druid.pool.DruidDataSource.class);
				beanDefinitionBuilder.addPropertyValue("driverClassName", info.getDriverClassName());
				beanDefinitionBuilder.addPropertyValue("url", info.getUrl());
				beanDefinitionBuilder.addPropertyValue("username", info.getUsername());
				beanDefinitionBuilder.addPropertyValue("password", info.getPassword());

				Map<String, String> map = toMap(dataSourceConfigProperties);
				for(String key : map.keySet()){
					beanDefinitionBuilder.addPropertyValue(key, map.get(key));
				}

				beanDefinitionBuilder.setInitMethodName("init");
				beanDefinitionBuilder.setDestroyMethodName("close");
				beanFactory.registerBeanDefinition(tenantId, beanDefinitionBuilder.getBeanDefinition());
			}
			DataSource newDataSource = (DataSource)applicationContext.getBean(tenantId);
			dynamicDataSource.addDataSource(tenantId, newDataSource);
		}
	}
	/**
	 * 通过DruidDataSourceFactory创建DataSource.
	 * @param info 指定tenantId.
	 * @return 返回对应的DataSourceInfo.
	 */
	public DataSource createDataSource(DataSourceInfo info) {
		Properties props = new Properties();
		props.put("driverClassName", info.getDriverClassName());
		props.put("url", info.getUrl());
		props.put("username", info.getUsername());
		props.put("password", info.getPassword());
		Map<String, String> map = toMap(dataSourceConfigProperties);
		for (String key : map.keySet()) {
			props.put(key, map.get(key));
		}

		try {
			return DruidDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * JavaBean转Map对象.
	 * @param javaBean 指定对象.
	 * @return 转换后的Map对象.
	 */
	public static Map<String, String> toMap(Object javaBean) {
		Map<String, String> result = new HashMap<>();
		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {
			try	{
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);

					Object value = method.invoke(javaBean, (Object[])null);
					if (null != value) {
						result.put(field, value.toString());
					}
				}
			} catch (Exception e){
			}
		}
		return result;
	}
}
