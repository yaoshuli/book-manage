package com.book.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 采购表与订单书籍表关联信息表数据库操作映射接口
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:16:54
 */
@Mapper
public interface PurchaseJoinOrderBookMapper {

    int insertJoin(@Param("orderBookId") String orderBookId, @Param("purchaseId")String purchaseId);
}
