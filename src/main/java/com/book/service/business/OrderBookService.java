package com.book.service.business;

import com.book.model.business.OrderBookDO;

/**
 * 订购书籍服务接口
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:16:22
 */
public interface OrderBookService {

    /**
     * 新增订购书籍信息
     * @return
     */
     boolean addOrderBook(OrderBookDO orderBookDO);
}
