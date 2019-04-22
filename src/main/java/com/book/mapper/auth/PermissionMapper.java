package com.book.mapper.auth;

import com.book.mapper.base.BaseMapper;
import com.book.model.auth.PermissionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限数据库操作接口
 */
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionDO> {
}
