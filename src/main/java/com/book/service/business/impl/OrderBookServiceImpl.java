package com.book.service.business.impl;

import com.book.mapper.business.OrderBookMapper;
import com.book.model.business.OrderBookDO;
import com.book.service.business.OrderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * 订购书籍服务接口实现类
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:16:22
 */
@Service
public class OrderBookServiceImpl implements OrderBookService {

    @Autowired(required = false)
    private OrderBookMapper orderBookMapper;

    @Override
    public boolean addOrderBook(OrderBookDO orderBookDO) {

        //设置新增时间
        orderBookDO.setGmtCreate(LocalDate.now());

        return orderBookMapper.insert(orderBookDO)>0;
    }
}
