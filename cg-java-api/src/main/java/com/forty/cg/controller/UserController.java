package com.forty.cg.controller;

import com.alibaba.fastjson2.JSONObject;
import com.forty.cg.common.BaseResponse;
import com.forty.cg.service.UserService;
import com.forty.cg.service.WeChatService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    WeChatService weChatService;

    @GetMapping("/wechat/login")
    public BaseResponse<Map<String, Object>> wechatLogin(@RequestParam String code) {
        JSONObject object = weChatService.wechatLogin(code);
        String openid = object.getString("openid");
        Map<String, Object> map = userService.userLogin(openid);
        map.put("openid", openid);
        map.put("session_key", object.getString("session_key"));
        return new BaseResponse<>(map);
    }

    @GetMapping("/wechat/get_user_info")
    public BaseResponse<Map<String, Object>> getUserInfo(@RequestParam String openid) {

        return null;
    }
}
