package com.qf.posp.serv.common.route;

/**
 * 名称: RoutingContext.java <br>
 * 描述: 多租户路由参数类<br>
 * 最近修改时间:2017/7/12 14:24 <br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/7/12 14:24
 */
public class RoutingContext {

	private String merchantCode;

	public RoutingContext(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
}
