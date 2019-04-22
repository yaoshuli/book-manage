package com.book.model.auth;

import com.book.enums.auth.UserStateEnum;
import com.book.model.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户表实体类
 */
@Table(name = "tb_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDO extends BaseDO  implements Serializable {



    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 状态
     */
    @Column(name = "user_state")
    private UserStateEnum userState;


}
