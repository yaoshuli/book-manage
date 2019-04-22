package com.book.mapper.business;

import com.book.model.business.ReadersDO;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * 读者管理数据库操作映射接口
 *
 * @author:YaoShuLi
 * @Date:2019/4/1 0001
 * @Time:19:06
 */
@Mapper
public interface ReadersMapper extends BaseMapper<ReadersDO> {

}
