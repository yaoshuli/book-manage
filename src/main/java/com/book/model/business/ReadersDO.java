package com.book.model.business;

import com.book.enums.base.SexEnum;
import com.book.enums.business.ReadersStateEnum;
import com.book.model.base.BaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 读者实体类
 *
 * @author:YaoShuLi
 * @Date:2019/4/1 0001
 * @Time:19:07
 */
@Data
@Table(name="tb_readers")
public class ReadersDO extends BaseDO{

    /**
     * 读者姓名
     */
    @Column(name="readers_name")
    private String readersName;

    /**
     * 借阅密码
     */
    @Column(name="password")
    private String password;

    /**
     * 读者手机号
     */
    @Column(name="readers_phone")
    private String readersPhone;

    /**
     * 读者身份证号
     */
    @Column(name="id_card")
    private String idCard;

    /**
     * 读者籍贯
     */
    @Column(name="native_place")
    private String nativePlace;

    /**
     * 读者性别
     */
    @Column(name="readers_sex")
    private SexEnum readersSex;

    /**
     * 读者卡状态
     */
    @Column(name="readers_state")
    private ReadersStateEnum readersState;

    /**
     * 读者学号
     */
    @Column(name="student_number")
    private String studentNumber;

    /**
     * 读者现住址
     */
    @Column(name="address")
    private String address;

    /**
     * 拉黑原因描述
     */
    @Column(name="blacklist_description")
    private String blacklistDescription;

    /**
     * 读者卡所属是否知情
     */
    @Column(name="is_contact")
    private String isContact;

}
