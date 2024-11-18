package com.forty.cg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forty.cg.common.BaseResponse;
import com.forty.cg.schema.TokenData;

import java.util.Map;

public interface UserService {

    Map<String, Object> userLogin(String openid);
}
