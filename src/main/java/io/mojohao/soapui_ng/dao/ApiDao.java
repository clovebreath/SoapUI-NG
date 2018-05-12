package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.entity.ChartTypeDto;

import java.util.List;
import java.util.Map;

/**
 * @author mojohao
 */
public interface ApiDao {
    /**
     * 获取所有api
     * @return api list
     */
    List<Api> getAllApis();

    /**
     * 根据id查询api
     * @param id
     * @return api
     */
    Api queryApiById(int id);

    /**
     * 根据条件查询api
     * @param api 条件容器
     * @return
     */
    List<Api> queryApiByCondition(Api api);

    /**
     * 根据条件查询api 分页
     * @param map 条件容器
     * @return
     */
    List<Api> queryApiByPage(Map map);

    /**
     * 删除api
     * @param id
     * @return
     */
    int deleteApiById(int id);

    /**
     * 更新api
     * @param api
     * @return
     */
    int updateApi(Api api);

    /**
     * 插入新的api
     * @param api
     * @return
     */
    int insertApi(Api api);

    /**
     * 按照API_TYPE统计数据
     * @return
     */
    List<ChartTypeDto> categoryByType();

    /**
     * 按照ACCESS_MODE统计数据
     * @return
     */
    List<ChartTypeDto> categoryByAccessMode();

    /**
     * 统计一共多少个
     * @return
     */
    int countAll();

    /**
     * 统计符合条件的有多少个
     * @return
     */
    int queryAmount(Map map);
}
