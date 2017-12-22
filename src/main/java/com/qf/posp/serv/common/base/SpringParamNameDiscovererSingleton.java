package com.qf.posp.serv.common.base;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

/**
 * 名称: SpringParamNameDiscovererSingleton.java <br>
 * 描述: LocalVariableTableParameterNameDiscoverer 单例类<br>
 * 最近修改时间:2017/6/30 9:58 <br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/30 9:58
 */
public enum SpringParamNameDiscovererSingleton {

	INSTANCE;

	private LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer;

	SpringParamNameDiscovererSingleton() {
		localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
	}

	public LocalVariableTableParameterNameDiscoverer getInstance() {
		return localVariableTableParameterNameDiscoverer;
	}
}
