package com.book.controller.system;

import com.book.model.auth.UserDO;
import com.book.service.auth.UserService;
import com.book.utlis.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户登录控制器
 * 用于处理用户登入登出操作
 * @author:YaoShuLi
 * @Date:2019/3/30 0030
 * @Time:16:45
 */
@Api("用户登录控制器")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    @ResponseBody
    public Message login(String username, String password){
        try {
            // 从SecurityUtils里边创建一个 subject
            Subject subject = SecurityUtils.getSubject();
            // 在认证提交前准备 token（令牌）
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            // 执行认证登陆
            subject.login(token);

        }catch (AccountException e){
            return new Message("500",e.getMessage().toString());

        }

        //设置用户信息
        Session session =SecurityUtils.getSubject().getSession();
        UserDO userDO = userService.findByUserName(username);
        session.setAttribute("userInfo",userDO);

        return new Message("200","登录成功！");
    }


}
