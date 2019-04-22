package com.book.mapper.base;

import tk.mybatis.mapper.common.*;

import java.util.List;

/**
 * 基础数据库操作接口
 * 继承了tkMybatis中的Mapper接口
 * 该接口中会对我们提供一些列增删改查的方法
 * @param <T>
 */
public interface BaseMapper<T>  extends Mapper<T> {

}
