package com.forty.cg.service.impl;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forty.cg.config.GlobalConfiguration;
import com.forty.cg.mapper.UserPermissionMapper;
import com.forty.cg.mapper.UserProfileMapper;
import com.forty.cg.mapper.UserRoleMapper;
import com.forty.cg.model.UserProfile;
import com.forty.cg.schema.TokenData;
import com.forty.cg.schema.UserPermissionBase;
import com.forty.cg.schema.UserRoleBase;
import com.forty.cg.service.UserService;
import com.forty.cg.utils.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserProfileMapper userProfileMapper;

    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    UserPermissionMapper userPermissionMapper;

    @Resource
    GlobalConfiguration globalConfiguration;

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
    public Map<String, Object> userLogin(String openid) {
        if (openid == null)  return null;
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
        Map map = JSON.parseObject(JSON.toJSONString(tokenData), Map.class);
        String token = JWTUtils.encrypt(map, globalConfiguration.getSecretKey());

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("roles", userRoleList);
        return result;
    }
}
