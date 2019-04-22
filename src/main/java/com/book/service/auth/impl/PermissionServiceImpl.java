package com.book.service.auth.impl;

import com.book.mapper.auth.PermissionMapper;
import com.book.model.auth.PermissionDO;
import com.book.service.auth.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 权限服务接口实现类
 *
 * @author:YaoShuLi
 * @Date:2019/3/29 0029
 * @Time:18:13
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    /**
     * 定义Mapper操作对象
     */
    @Autowired(required = false)
    PermissionMapper permissionMapper;



    /**
     * 查询所有权限信息
     * @return 权限信息
     */
    @Override
    public List<PermissionDO> findAll() {
        return permissionMapper.selectAll();
    }

    /**
     *  新增权限对象
     * @param permissionDO  权限对象
     * @return
     */
    @Override
    public boolean addPermission(PermissionDO permissionDO) {

        //给创建时间赋一个值
        permissionDO.setGmtCreate(LocalDate.now());

        return permissionMapper.insert(permissionDO)>0?true:false;
    }
}
