package com.forty.cg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forty.cg.model.AuthPermission;
import com.forty.cg.schema.UserPermissionBase;

import java.util.List;

public interface UserPermissionMapper extends BaseMapper<AuthPermission> {

    List<UserPermissionBase> getUserPermissionListByRoleId(List<String> roleIds);

    List<UserPermissionBase> getUserPermissionListByUserId(String userId);
}
