package com.forty.cg.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user_role")
public class UserRole implements Serializable {
    @TableId("role_id")
    private String roleId;
    @TableField("role_name")
    private String roleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}