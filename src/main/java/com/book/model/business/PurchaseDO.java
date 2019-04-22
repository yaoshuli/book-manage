package com.book.model.business;

import com.book.enums.business.PurchaseState;
import com.book.model.base.BaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;


/**
 * 采购实体类
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:14:24
 */
@Data
@Table(name = "tb_purchase")
public class PurchaseDO extends BaseDO {

    /**
     * 采购计划
     */
    @Column(name = "purchase_name")
    private  String purchaseName;

    /**
     * 采购描述
     */
    @Column(name = "purchase_description")
    private  String purchaseDescription;

    /**
     * 采购书籍数量
     */
    @Column(name = "purchase_number")
    private  String purchaseNumber;

    /**
     * 采购预算金额
     */
    @Column(name = "purchase_money")
    private  String purchaseMoney;

    /**
     * 采购状态
     */
    @Column(name = "purchase_state")
    private PurchaseState purchaseState;

    /**
     * 负责人姓名
     */
    @Column(name = "principal_name")
    private  String principalName ;

    /**
     * 负责人联系方式
     */
    @Column(name = "principal_phone")
    private  String principalPhone ;



}
