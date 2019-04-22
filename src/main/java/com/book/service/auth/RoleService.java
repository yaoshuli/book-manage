package com.book.service.auth;

import com.book.model.auth.RoleDO;
import java.util.List;

/**
 * 角色信息服务接口
 */
public interface RoleService {

    /**
     * 查询所有角色信息
     * @return 所有角色信息
     */
    List<RoleDO> findAll();

    /**
     * 新增角色信息
     * @param roleDO 角色信息
     * @return  新增结果
     */
    boolean addRole(RoleDO roleDO);

    /**
     * 根据用户id查询对应的角色信息
     * @param id    用户id
     * @return  角色信息集合
     */
    List<RoleDO> findRolesByUserId(String id);
}
