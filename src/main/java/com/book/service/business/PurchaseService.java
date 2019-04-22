package com.book.service.business;

import com.book.model.business.OrderBookDO;
import com.book.model.business.PurchaseDO;
import com.book.utlis.PageResult;

import java.util.List;

/**
 * 采购计划服务接口
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:15:53
 */
public interface PurchaseService {

    /**
     * 新增采购计划
     * @param purchaseDO    采购计划信息
     * @param orderBookDOList 订购书籍信息
     * @return  新增结果
     */
    boolean addPurchase(PurchaseDO purchaseDO, List<OrderBookDO> orderBookDOList) throws Exception;


    /**
     * 分页查询采购计划
     * @param purchaseDO    查询条件
     * @param rows  每页行数
     * @param page  第几页
     * @return
     */
    PageResult<PurchaseDO> findPurchase(PurchaseDO purchaseDO, Integer rows, Integer page);
}
