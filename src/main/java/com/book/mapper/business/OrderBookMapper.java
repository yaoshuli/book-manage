package com.book.mapper.business;

import com.book.mapper.base.BaseMapper;
import com.book.model.business.OrderBookDO;
import org.apache.ibatis.annotations.Mapper;

/**
 *  订单数据数据库操作映射接口
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:16:14
 */
@Mapper
public interface OrderBookMapper extends BaseMapper<OrderBookDO> {
}
