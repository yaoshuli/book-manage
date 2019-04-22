package com.book.service.business.impl;


import com.book.enums.business.PurchaseState;
import com.book.mapper.business.PurchaseJoinOrderBookMapper;
import com.book.mapper.business.PurchaseMapper;
import com.book.model.business.OrderBookDO;
import com.book.model.business.PurchaseDO;
import com.book.service.business.OrderBookService;
import com.book.service.business.PurchaseService;
import com.book.utlis.IdUtils;
import com.book.utlis.PageResult;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 采购计划服务接口实现类
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:15:55
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired(required = false)
    private PurchaseMapper purchaseMapper;

    @Autowired(required = false)
    private PurchaseJoinOrderBookMapper purchaseJoinOrderBookMapper;

    @Autowired
    private OrderBookService orderBookService;

    public boolean addPurchase(PurchaseDO purchaseDO) {

        //默认初始化状态为采购中
        purchaseDO.setPurchaseState(PurchaseState.EXECUTING);

        //添加创建时间
        purchaseDO.setGmtCreate(LocalDate.now());


        return purchaseMapper.insert(purchaseDO) > 0 ? true:false;
    }


    @Transactional
    @Override
    public boolean addPurchase(PurchaseDO purchaseDO, List<OrderBookDO> orderBookDOList){

        //获取书籍总数量
        int bookSumNumber = 0;
        for (OrderBookDO orderBookDO:orderBookDOList) {
            bookSumNumber +=orderBookDO.getOrderBookNumber();
        }

        //设置书籍数量
        purchaseDO.setPurchaseNumber(bookSumNumber+"");

        //设置采购计划id
        purchaseDO.setId(IdUtils.getIncreaseIdByCurrentTimeMillis());

        //新增采购计划信息
        if(!this.addPurchase(purchaseDO)){
            return false;
        }

        //新增该采购计划 订单书籍信息
        for (OrderBookDO orderBookDO : orderBookDOList) {
            //设置书籍id
            orderBookDO.setId(IdUtils.getRandomIdByUUID());
            //调用方法新增单个书籍
            boolean isSuccess = orderBookService.addOrderBook(orderBookDO);
            if(!isSuccess) {
                return false;
            }
            //新增关联表信息
            isSuccess = purchaseJoinOrderBookMapper.insertJoin(orderBookDO.getId(),purchaseDO.getId())>0;
            if(!isSuccess) {
                return false;
            }

        }

        return true;
    }

    @Override
    public PageResult<PurchaseDO> findPurchase(PurchaseDO purchaseDO, Integer rows, Integer page) {
        //获取总数
        int count =  purchaseMapper.selectCount(purchaseDO);
        //使用PageHelper进行分页处理
        Page<PurchaseDO> pages= PageHelper.startPage(page, rows);
        List<PurchaseDO> purchaseDOPage = pages.doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
                purchaseMapper.select(purchaseDO);
            }
        });
        return new PageResult<PurchaseDO>(count,purchaseDOPage);
    }
}
