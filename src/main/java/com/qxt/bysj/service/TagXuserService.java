package com.qxt.bysj.service;

import com.qxt.bysj.domain.TagXuser;

/**
 * @Author qxt
 * @Date 2020/2/13 19:10
 * @Version 1.0
 */
public interface TagXuserService extends BaseService<TagXuser> {
    int deleteByUserId(Integer userId);
}
