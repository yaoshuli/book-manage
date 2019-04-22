package com.book.controller.auth;

import com.book.model.auth.PermissionDO;
import com.book.service.auth.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限控制器
 * @author:YaoShuLi
 * @Date:2019/3/29 0029
 * @Time:18:07
 */
@RestController
@RequestMapping("/auth/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("查询所有权限信息")
    @GetMapping("/findAll")
    public List<PermissionDO> findAll(){
        return permissionService.findAll();
    }


    @ApiOperation("新增权限信息")
    @PostMapping("/addPermission")
    public String addPermission(PermissionDO permissionDO){

        boolean result= permissionService.addPermission(permissionDO);
        if(result == true){
            return "成功!";
        }
        return "失败!";
    }
}
