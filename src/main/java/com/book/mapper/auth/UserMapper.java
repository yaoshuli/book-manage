package com.book.mapper.auth;

import com.book.mapper.base.BaseMapper;
import com.book.model.auth.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据库操作接口
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
