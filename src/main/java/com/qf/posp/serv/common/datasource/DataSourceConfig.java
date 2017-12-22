package com.qf.posp.serv.common.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 名称: DataSourceConfig.java <br>
 * 描述: 注册一个DynamicDataSource对象到Spring容器中.<br>
 * 类型: JAVA <br>
 * 最近修改时间:2017/6/30 19:27.<br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/30 19:27.
 */
@Configuration
public class DataSourceConfig {

	@Autowired
	AbstractDataSourceManager dataSourceManager;

	@Bean
	public DynamicDataSource dataSource() {

		Map<String, DataSourceInfo> properties = dataSourceManager.initDataSources();

		Map<Object, Object> targetDataSources = new HashMap<>();

		for(String tenantId : properties.keySet()){
			DataSource dataSource = dataSourceManager.createDataSource(properties.get(tenantId));
			targetDataSources.put(tenantId, dataSource);
		}

		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		dynamicDataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dynamicDataSource.setDefaultTargetDataSource(null);// 默认为null，必须指定数据

		return dynamicDataSource;
	}
}
