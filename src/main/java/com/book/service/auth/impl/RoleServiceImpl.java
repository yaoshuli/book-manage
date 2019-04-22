package com.book.service.auth.impl;

import com.book.mapper.auth.RoleMapper;
import com.book.model.auth.RoleDO;
import com.book.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 用户信息实现类
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * 定义Mapper操作对象
     */
    @Autowired(required = false)
    RoleMapper roleMapper;

    /**
     *  查询所有角色信息方法
     * @return 所有角色信息
     */
    @Override
    public List<RoleDO> findAll() {

        return roleMapper.selectAll();
    }

    /**
     * 新增角色
     * @param roleDO 角色信息
     * @return  新增结果
     */
    @Override
    public boolean addRole(RoleDO roleDO) {

        //赋值创建时间
        roleDO.setGmtCreate(LocalDate.now());

        return roleMapper.insert(roleDO)>0?true:false;
    }

    /**
     * 根据用户id查询对应的角色信息
     * @param id    用户id
     * @return  角色信息集合
     */
    @Override
    public List<RoleDO> findRolesByUserId(String id) {
        return roleMapper.selectRolesByUserId(id);
    }
}
