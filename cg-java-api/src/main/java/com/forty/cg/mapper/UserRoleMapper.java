package com.forty.cg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forty.cg.model.UserRole;
import com.forty.cg.schema.UserRoleBase;

import java.util.List;

public interface UserRoleMapper  extends BaseMapper<UserRole> {
    List<UserRoleBase> getUserRoleList(String userId);
}