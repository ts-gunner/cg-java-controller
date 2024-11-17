package com.forty.cg.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forty.cg.common.BaseResponse;
import com.forty.cg.common.CodeStatus;
import com.forty.cg.mapper.UserPermissionMapper;
import com.forty.cg.mapper.UserProfileMapper;
import com.forty.cg.mapper.UserRoleMapper;
import com.forty.cg.model.UserProfile;
import com.forty.cg.model.UserRoleMapping;
import com.forty.cg.schema.TokenData;
import com.forty.cg.schema.UserPermissionBase;
import com.forty.cg.schema.UserRoleBase;
import com.forty.cg.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserProfileMapper userProfileMapper;

    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    UserPermissionMapper userPermissionMapper;

    public void createUserProfile(String openid){
        QueryWrapper<UserProfile> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        Long result = userProfileMapper.selectCount(wrapper);
        if (result == 0) {
            UserProfile userProfile = new UserProfile();
            userProfile.setOpenid(openid);
            userProfile.setNickname("微信用户");
            userProfileMapper.insert(userProfile);
        }
    }

    @Override
    public BaseResponse<TokenData> userLogin(String openid) {
        if (openid == null)  return new BaseResponse<>(CodeStatus.PARAM_ERROR);
        // create user profile
        this.createUserProfile(openid);
        // get role list base on openid
        List<UserRoleBase> userRoleList = userRoleMapper.getUserRoleList(openid);
        List<String> roleIds = userRoleList.stream().map(UserRoleBase::getRoleId).toList();
        List<UserPermissionBase> rolePermissionList = userPermissionMapper.getUserPermissionListByRoleId(roleIds);
        List<UserPermissionBase>  userPermissionList =  userPermissionMapper.getUserPermissionListByUserId(openid);
        List<UserPermissionBase> permissionList = Stream.concat(rolePermissionList.stream(), userPermissionList.stream()).toList();
        TokenData tokenData = new TokenData();
        tokenData.setUserId(openid);
        tokenData.setRoles(userRoleList);
        tokenData.setPermissions(permissionList);
        BaseResponse<TokenData> response = new BaseResponse<>();
        response.setData(tokenData);
        return response;
    }
}
