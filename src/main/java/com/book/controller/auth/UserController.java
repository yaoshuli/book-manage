package com.book.controller.auth;

import com.book.model.auth.UserDO;
import com.book.service.auth.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 * @author:YaoShuLi
 * @Date:2019/3/29 0029
 * @Time:18:07
 */
@Api("用户信息")
@RestController
@RequestMapping("/auth/user")
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户信息")
    @GetMapping("/findAll")
    public List<UserDO> findAll(){
        return userService.findAll();
    }

    @ApiOperation("新增角色信息")
    @PostMapping("/addUser")
    public String addUser(UserDO  userDO){

        boolean result = userService.addUser(userDO);

        if(result == true){
            return "成功!";
        }
        return "失败!";
    }
}
