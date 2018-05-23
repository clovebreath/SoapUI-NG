package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.entity.ChartTypeDto;

import java.util.List;
import java.util.Map;

public interface ApiService {
    /**
     * 查询全部
     * @return
     */
    List<Api> getAllApis();

    /**
     * 查询一个
     * @param id
     * @return
     */
    Api queryApiById(int id);

    /**
     * 根据条件查询
     * @param api
     * @return
     */
    List<Api> queryApiByCondition(Api api);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteApiById(int id);

    /**
     * 更新
     * @param api
     * @return
     */
    int updateApi(Api api);

    /**
     * 插入
     * @param api
     * @return
     */
    int insertApi(Api api);

    /**
     * 查询总数
     * @param param
     * @return
     */
    int queryAmount(Map param);

    /**
     * 分页
     * @param map
     * @return
     */
    List<Api> queryApiByPage(Map map);
    List<ChartTypeDto> categoryByType();
    List<ChartTypeDto> categoryByAccessMode();

}
