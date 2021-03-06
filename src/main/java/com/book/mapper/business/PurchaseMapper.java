package com.book.mapper.business;

import com.book.mapper.base.BaseMapper;
import com.book.model.business.PurchaseDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购计划数据库操作映射接口
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:15:56
 */
@Mapper
public interface PurchaseMapper extends BaseMapper<PurchaseDO> {
}
