package com.book.model.auth;

import com.book.model.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_role")
public class RoleDO extends BaseDO  implements Serializable {


    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 备注信息
     */
    @Column(name = "remarks")
    private String remarks;
}
