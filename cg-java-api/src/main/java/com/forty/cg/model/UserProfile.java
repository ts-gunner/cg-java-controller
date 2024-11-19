package com.forty.cg.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Data
@TableName("user_profile")
public class UserProfile implements Serializable {

    private long id;
    /**
     * 微信提供当前小程序的openid，标识个人id
     */
    private String openid;
    /**
     * 微信提供多平台的unionid，标识同一用户在不同应用的id
     */
    private String unionid;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    @TableField("phone_number")
    private String phoneNumber;
    /**
     * 头像地址
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 更新时间
     */
    private Date update_time;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
