package com.qf.posp.serv.common.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 名称: DataSourceConfigProperties.java <br>
 * 描述: 数据源相关配置参数<br>
 * 最近修改时间:2017/7/3 14:09 <br>
 *
 * @author YanzhiLi
 * @version [版本号;private String V1.0]
 * @since 2017/7/3 14:09
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Component
public class DataSourceConfigProperties {

	private String defaultAutoCommit;
	private String defaultReadOnly;
	private String defaultTransactionIsolation;
	private String defaultCatalog;
	private String maxActive;
	private String maxIdle;
	private String minIdle;
	private String initialSize;
	private String maxWait;
	private String testOnBorrow;
	private String testOnReturn;
	private String timeBetweenEvictionRunsMillis;
	private String numTestsPerEvictionRun;
	private String minEvictableIdleTimeMillis;
	private String testWhileIdle;
	private String filters;
	private String validationQuery;
	private String validationQueryTimeout;
	private String initConnectionSqls;
	private String accessToUnderlyingConnectionAllowed;
	private String removeAbandoned;
	private String removeAbandonedTimeout;
	private String logAbandoned;
	private String poolPreparedStatements;
	private String maxOpenPreparedStatements;
	private String connectionProperties;
	private String exceptionSorter;
	private String init;
	private String name;

	public String getDefaultAutoCommit() {
		return defaultAutoCommit;
	}

	public void setDefaultAutoCommit(String defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}

	public String getDefaultReadOnly() {
		return defaultReadOnly;
	}

	public void setDefaultReadOnly(String defaultReadOnly) {
		this.defaultReadOnly = defaultReadOnly;
	}

	public String getDefaultTransactionIsolation() {
		return defaultTransactionIsolation;
	}

	public void setDefaultTransactionIsolation(String defaultTransactionIsolation) {
		this.defaultTransactionIsolation = defaultTransactionIsolation;
	}

	public String getDefaultCatalog() {
		return defaultCatalog;
	}

	public void setDefaultCatalog(String defaultCatalog) {
		this.defaultCatalog = defaultCatalog;
	}

	public String getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(String maxActive) {
		this.maxActive = maxActive;
	}

	public String getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(String maxIdle) {
		this.maxIdle = maxIdle;
	}

	public String getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(String minIdle) {
		this.minIdle = minIdle;
	}

	public String getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(String initialSize) {
		this.initialSize = initialSize;
	}

	public String getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(String maxWait) {
		this.maxWait = maxWait;
	}

	public String getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(String testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public String getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(String testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public String getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public String getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(String numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public String getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public String getTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(String testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public String getValidationQueryTimeout() {
		return validationQueryTimeout;
	}

	public void setValidationQueryTimeout(String validationQueryTimeout) {
		this.validationQueryTimeout = validationQueryTimeout;
	}

	public String getInitConnectionSqls() {
		return initConnectionSqls;
	}

	public void setInitConnectionSqls(String initConnectionSqls) {
		this.initConnectionSqls = initConnectionSqls;
	}

	public String getAccessToUnderlyingConnectionAllowed() {
		return accessToUnderlyingConnectionAllowed;
	}

	public void setAccessToUnderlyingConnectionAllowed(String accessToUnderlyingConnectionAllowed) {
		this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
	}

	public String getRemoveAbandoned() {
		return removeAbandoned;
	}

	public void setRemoveAbandoned(String removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	public String getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	public void setRemoveAbandonedTimeout(String removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	public String getLogAbandoned() {
		return logAbandoned;
	}

	public void setLogAbandoned(String logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	public String getPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(String poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	public String getMaxOpenPreparedStatements() {
		return maxOpenPreparedStatements;
	}

	public void setMaxOpenPreparedStatements(String maxOpenPreparedStatements) {
		this.maxOpenPreparedStatements = maxOpenPreparedStatements;
	}

	public String getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	public String getExceptionSorter() {
		return exceptionSorter;
	}

	public void setExceptionSorter(String exceptionSorter) {
		this.exceptionSorter = exceptionSorter;
	}

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
