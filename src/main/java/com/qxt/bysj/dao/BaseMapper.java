package com.qxt.bysj.dao;

import java.util.List;
import java.util.Map;

/**
 * @Author qxt
 * @Date 2020/2/13 11:21
 * @Version 1.0
 */
public interface BaseMapper<T> {
    int deleteByPrimaryKey(Integer id);


    int insert(T record);


    int insertSelective(T record);


    T selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    /**
     * 查询全部
     * @return
     */
    List<T> find(Map<String, Object> map);

    /**
     * 分页查询
     * @return
     */
    List<T> findPage(Map<String, Object> map);
}
