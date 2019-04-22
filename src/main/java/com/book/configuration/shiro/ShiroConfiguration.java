package com.book.configuration.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IDEA
 *
 * @author:YaoShuLi
 * @Date:2019/3/30 0030
 * @Time:10:58
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

    /**
     * 自定义一个安全管理器
     * 采用了shiro提供的DefaultWebSecurityManager中的一些默认配置
     * 自定义了realm验证
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(customRealm());
        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){

        //定义shiro拦截器工厂对象 用于设置shiro
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/html/login.html");

        //设置成功是shiro跳转路径
        shiroFilterFactoryBean.setSuccessUrl("/html/index.html");

        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/html/login.html");



        /**
         * 定义存储资源过滤Map,用于设置不同资源下过滤器选择
         *  authc:这个过滤器用于判断当前用户是否已经完成认证,已经认证就放行，如果当前用户没有认证，跳转到登录页面
         *  anon:允许在不登录情况下匿名访问
         *  roles[xxx]: 必须有xxx角色才可以访问
         *
         */
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //静态资源不进行拦截
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");


        //开放登录界面
        filterChainDefinitionMap.put("/login", "anon");

        //登出请求
        filterChainDefinitionMap.put("/logout", "logout");
        //查看API文档需要管理员权限
//        filterChainDefinitionMap.put("/swagger-ui.html", "roles[admin]");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");


        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        //将设置好的过滤器Map赋值到shiro中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //设置shiro安全管理器，其中配置了自定义的Realm
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        return shiroFilterFactoryBean;
    }




}
