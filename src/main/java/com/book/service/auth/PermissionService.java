package com.book.service.auth;

import com.book.model.auth.PermissionDO;

import java.util.List;

/**
 * 权限服务接口
 *
 * @author:YaoShuLi
 * @Date:2019/3/29 0029
 * @Time:18:11
 */
public interface PermissionService {
    /**
     * 查询所有权限信息
     * @return 权限信息
     */
    List<PermissionDO> findAll();

    /**
     * 新增权限信息
     * @param permissionDO  权限对象
     * @return  新增结果
     */
    boolean addPermission(PermissionDO permissionDO);
}
