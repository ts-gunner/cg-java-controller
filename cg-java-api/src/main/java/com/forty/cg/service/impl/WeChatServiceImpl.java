package com.forty.cg.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.forty.cg.config.WeChatConfiguration;
import com.forty.cg.service.WeChatService;
import com.forty.cg.utils.HttpUtils;
import jakarta.annotation.Resource;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatServiceImpl implements WeChatService {

    @Resource
    private WeChatConfiguration weChatConfiguration;

    @Override
    public JSONObject wechatLogin(String code) {
        String url = MessageFormat.format(
                "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code",
                weChatConfiguration.getAppId(), weChatConfiguration.getAppSecret(), code
        );
        String result = HttpUtils.get(url, new HashMap<>());
        return JSON.parseObject(result);
    }

}
