package com.qf.posp.serv.common.route;

import java.lang.annotation.*;

/**
 * 名称: RoutingDatasource.java <br>
 * 描述: 多租户路由注解<br>
 * 最近修改时间:2017/6/26 13:50 <br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/26 13:50
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoutingDatasource {

    String tenantId();

}
