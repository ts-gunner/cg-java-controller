package com.forty.cg.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("auth_permission")
public class AuthPermission implements Serializable {
    /**
     * 权限代码
     */
    @TableId
    private String permissionId;
    /**
     * 权限名称
     */
    private String permissionName;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
