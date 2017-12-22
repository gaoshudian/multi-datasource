package com.qf.posp.serv.common.datasource;

import com.qf.posp.serv.common.base.AbstractQuarkRoutingDataSource;
import com.qf.posp.serv.common.route.RoutingContextHolder;

import javax.sql.DataSource;

/**
 * 名称: DynamicDataSource.java <br>
 * 描述: 动态数据源.<br>
 * 类型: JAVA <br>
 * 最近修改时间:2017/6/7 19:27.<br>
 *
 * @author YanzhiLi
 * @version [版本号, V1.0]
 * @since 2017/6/7 19:27.
 */
public class DynamicDataSource extends AbstractQuarkRoutingDataSource {

    protected Object determineCurrentLookupKey() {
        return RoutingContextHolder.getContext();
    }

    public void addDataSource(String lookupKey, DataSource dataSource) {
        super.addDataSource(lookupKey, dataSource);
    }

    public boolean isExist(String lookupKey) {
        return super.isExist(lookupKey);
    }

}