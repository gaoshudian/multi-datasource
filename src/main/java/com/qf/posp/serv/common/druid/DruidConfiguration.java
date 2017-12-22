package com.qf.posp.serv.common.druid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 名称: DruidConfiguration.java <br>
 * 描述: 配置Druid连接池的web管控台，客户端不需要再配置（可以放到客户端中，有更高的自由度）.<br>
 * 类型: JAVA <br>
 * 时间: 2017/6/30 19:27.<br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/30 19:27.
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties({DruidStatProperties.class})
public class DruidConfiguration {

	/**
	 * 注册ServletRegistrationBean
	 * @return
	 */
	@Bean
	public ServletRegistrationBean registrationBean(DruidStatProperties properties) {
		DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
		ServletRegistrationBean registration = new ServletRegistrationBean();
		registration.setServlet(new StatViewServlet());
		registration.addUrlMappings(config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*");
		/** 初始化参数配置，initParams**/
		//白名单
		if (config.getAllow() != null) {
			registration.addInitParameter("allow", config.getAllow());
		}
		//IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
		if (config.getDeny() != null) {
			registration.addInitParameter("deny", config.getDeny());
		}
		//登录查看信息的账号密码.
		if (config.getLoginUsername() != null) {
			registration.addInitParameter("loginUsername", config.getLoginUsername());
		}
		if (config.getLoginPassword() != null) {
			registration.addInitParameter("loginPassword", config.getLoginPassword());
		}
		//是否能够重置数据.
		if (config.getResetEnable() != null) {
			registration.addInitParameter("resetEnable", config.getResetEnable());
		}
		return registration;
	}

	/**
	 * 注册FilterRegistrationBean
	 * @return
	 */
	@Bean
	public FilterRegistrationBean druidStatFilter(DruidStatProperties properties) {
		DruidStatProperties.WebStatFilter config = properties.getWebStatFilter();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		WebStatFilter filter = new WebStatFilter();
		registrationBean.setFilter(filter);
		//添加过滤规则.
		registrationBean.addUrlPatterns(config.getUrlPattern() != null ? config.getUrlPattern() : "/*");
		//添加不需要忽略的格式信息.
		registrationBean.addInitParameter("exclusions",
				config.getExclusions() != null ? config.getExclusions() : "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		if (config.getSessionStatEnable() != null) {
			registrationBean.addInitParameter("sessionStatEnable", config.getSessionStatEnable());
		}
		if (config.getSessionStatMaxCount() != null) {
			registrationBean.addInitParameter("sessionStatMaxCount", config.getSessionStatMaxCount());
		}
		if (config.getPrincipalSessionName() != null) {
			registrationBean.addInitParameter("principalSessionName", config.getPrincipalSessionName());
		}
		if (config.getPrincipalCookieName() != null) {
			registrationBean.addInitParameter("principalCookieName", config.getPrincipalCookieName());
		}
		if (config.getProfileEnable() != null) {
			registrationBean.addInitParameter("profileEnable", config.getProfileEnable());
		}
		return registrationBean;
	}
}
