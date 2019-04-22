package com.book.controller.auth;

import com.book.model.auth.RoleDO;
import com.book.service.auth.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色控制器
 * @author:YaoShuLi
 * @Date:2019/3/29 0029
 * @Time:18:07
 */
@Api("角色信息")
@RestController
@RequestMapping("/auth/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @ApiOperation("查询所有角色信息")
    @GetMapping("/findAll")
    public List<RoleDO> findAll(){
        return roleService.findAll();
    }

    @ApiOperation("新增角色信息")
    @PostMapping("/addRole")
    public String addRole(RoleDO roleDO){

        boolean result = roleService.addRole(roleDO);

        if(result == true){
            return "成功!";
        }
        return "失败!";
    }
}
