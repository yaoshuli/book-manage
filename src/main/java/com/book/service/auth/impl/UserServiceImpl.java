package com.book.service.auth.impl;

import com.book.mapper.auth.UserMapper;
import com.book.model.auth.UserDO;
import com.book.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 用户信息实现类
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 定义Mapper操作对象
     */
    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     *  查询所有用户信息方法
     * @return 所有用户信息
     */
    @Override
    public List<UserDO> findAll() {
        return userMapper.selectAll();
    }

    /**
     * 根据用户名称查询用户信息
     * @param userName  用户名
     * @return  用户信息
     */
    @Override
    public UserDO findByUserName(String userName) {
        //定义查询参数对象
        UserDO userDO = new UserDO();
        //设置用户名称参数
        userDO.setUserName(userName);

        //只获取查询到的第一条数据
        return userMapper.selectOne(userDO);
    }

    /**
     *  新增用户信息
     * @param userDO    用户对象
     * @return  结果
     */
    @Override
    public boolean addUser(UserDO userDO) {

        //赋值创建时间
        userDO.setGmtCreate(LocalDate.now());

        return userMapper.insert(userDO)>0?true:false;
    }
}
