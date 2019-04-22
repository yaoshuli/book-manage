package com.book.model.business;

import com.book.enums.business.OrderBookType;
import com.book.model.base.BaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 订单书籍实体类
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:16:08
 */
@Data
@Table(name = "tb_order_book")
public class OrderBookDO extends BaseDO {

    /**
     *  订单书籍名称
     */
    @Column(name = "order_book_name")
    private  String orderBookName;


    /**
     *  订单书籍数量
     */
    @Column(name = "order_book_number")
    private  Integer orderBookNumber;


    /**
     *  订单书籍作者
     */
    @Column(name = "order_book_author")
    private  String orderBookAuthor;

    /**
     *  订单书籍类型
     */
    @Column(name = "order_book_type")
    private OrderBookType orderBookType;
}
