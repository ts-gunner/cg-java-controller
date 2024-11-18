package com.forty.cg.service;

import com.alibaba.fastjson2.JSONObject;

public interface WeChatService {

    JSONObject wechatLogin(String code);
}
