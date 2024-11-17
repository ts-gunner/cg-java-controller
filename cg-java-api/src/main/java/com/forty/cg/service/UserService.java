package com.forty.cg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forty.cg.common.BaseResponse;
import com.forty.cg.schema.TokenData;

public interface UserService {

    BaseResponse<TokenData> userLogin(String openid);
}
