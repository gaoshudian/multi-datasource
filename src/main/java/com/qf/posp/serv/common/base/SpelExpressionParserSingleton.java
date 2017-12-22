package com.qf.posp.serv.common.base;

import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 名称: SpelExpressionParserSigleton.java <br>
 * 描述: SpelExpressionParser单例类<br>
 * 时间: 2017/6/30 10:05 <br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/30 10:05
 */
public enum SpelExpressionParserSingleton {

	INSTANCE;

	private SpelExpressionParser spelExpressionParser;

    SpelExpressionParserSingleton() {
		spelExpressionParser = new SpelExpressionParser();
    }

    public SpelExpressionParser getInstance() {
        return spelExpressionParser;
    }
}
