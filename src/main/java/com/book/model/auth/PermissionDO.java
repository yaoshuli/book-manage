package com.book.model.auth;

import com.book.model.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_permission")
public class PermissionDO extends BaseDO implements Serializable {

    /**
     * 权限名称
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 备注信息
     */
    @Column(name = "remarks")
    private String remarks;
}
