package com.forty.cg.schema;

import lombok.Data;

import java.util.List;

@Data
public class TokenData {
    String userId;
    List<UserRoleBase> roles;
    List<UserPermissionBase> permissions;
}
