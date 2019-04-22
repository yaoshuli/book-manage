package com.book.configuration.shiro;

import com.book.enums.auth.UserStateEnum;
import com.book.model.auth.RoleDO;
import com.book.model.auth.UserDO;
import com.book.service.auth.RoleService;
import com.book.service.auth.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.util.StringUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义用户信息验证器
 * 用于校验登陆用户，并获取用户角色权限信息
 * @author:YaoShuLi
 * @Date:2019/3/30 0030
 * @Time:11:13
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    /**
     * 获取身份验证信息
     * 获取及校验登陆用户的身份信息,验证是否为可用用户
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("--------------------账号信息校验开始--------------------");
        //将认证token转换为用户密码token,方便获取用户输入帐名和密码
        UsernamePasswordToken token= ( UsernamePasswordToken) authenticationToken;

        //调用数据库查询获取该用户账号信息
        UserDO userDO = userService.findByUserName(token.getUsername());

        /**
         *  1.验证是否查询到账号
         *  2.验证是否查询到密码
         *  3.验证账号是否被锁定
         *  4.验证密码是否正确
         */
        if(userDO == null){
            throw new AccountException("该账户不存在！");
        }else if(StringUtil.isEmpty(userDO.getPassword())){
            throw new AccountException("该账户密码存在问题!");
        }else if(UserStateEnum.LOCKING ==userDO.getUserState()){
            throw new AccountException("该账号已被锁定，请联系管理员!");
        }else if(!userDO.getPassword().equals(new String((char[]) token.getCredentials()))){
            throw new AccountException("该账号密码不正确,请检查!");
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(), userDO.getPassword(), getName());
    }

    /**
     * 获取授权信息
     * 用户获取该用户下的所有角色及权限信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("————-------------权限获取 ------------------");

        //获取用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //定义权限信息类，用于存储权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //查找用户信息
        UserDO userDO = userService.findByUserName(username);

        //根据用户id获取该用户下所有的角色
        List<RoleDO> roleDOList = roleService.findRolesByUserId(userDO.getId());

        //将获取到的角色转换成set存储
        Set<String> set = new HashSet(roleDOList);

        //将角色set设置到权限信息类中
        info.setRoles(set);

        return info;
    }
}
