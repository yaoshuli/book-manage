package com.book.mapper.auth;

import com.book.mapper.base.BaseMapper;
import com.book.model.auth.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色数据库操作接口
 */
@Mapper
public interface RoleMapper  extends BaseMapper<RoleDO> {

    /**
     *  根据用户信息查询对应的角色信息
     *  用户与角色之间存在关联表
     *  通过内连接sql语句来进行查询
     * @param id    用户id
     * @return  角色信息集合
     */
    List<RoleDO> selectRolesByUserId(@Param("userId") String id);
}
