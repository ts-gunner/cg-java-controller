package com.forty.cg.service;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeChatServiceTest {
    @Resource
    private WeChatService weChatService;

    @Test
    public void testWeChatLogin() {
        String code = "0e3WJYll2Ksqye4a3Hkl2MvY180WJYlg";
        JSONObject object = weChatService.wechatLogin(code);
        System.out.println(object.getString("openid"));
    }

}
