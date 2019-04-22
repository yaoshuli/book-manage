package com.book.service.auth;

import com.book.model.auth.UserDO;

import java.util.List;

/**
 * 用户信息服务接口
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return 所有用户信息
     */
    List<UserDO> findAll();

    /**
     * 根据用户名称查询用户信息
     * @param userName  用户名
     * @return  用户信息
     */
    UserDO findByUserName(String userName);

    /**
     * 新增用户信息
     * @param userDO    用户对象
     * @return
     */
    boolean addUser(UserDO userDO);
}
