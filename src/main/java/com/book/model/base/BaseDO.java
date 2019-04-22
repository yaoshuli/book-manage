package com.book.model.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DO基础类提供表共有字段
 */
@Data
public class BaseDO implements Serializable {


    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;


    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private LocalDate gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private LocalDate gmtModified;
}
