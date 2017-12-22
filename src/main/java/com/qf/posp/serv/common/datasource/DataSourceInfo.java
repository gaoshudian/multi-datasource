package com.qf.posp.serv.common.datasource;

/**
 * 名称: DataSourceInfo.java <br>
 * 描述: DataSource基本信息<br>
 * 最近修改时间:2017/6/30 17:34 <br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/30 17:34
 */
public class DataSourceInfo {

	private String tenantId;

	private String driverClassName;

	private String url;

	private String username;

	private String password;

	public DataSourceInfo(String tenantId, String driverClassName, String url, String username, String password) {
		this.tenantId = tenantId;
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
