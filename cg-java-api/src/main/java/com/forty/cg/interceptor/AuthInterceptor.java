package com.forty.cg.interceptor;

import com.alibaba.fastjson2.JSON;
import com.forty.cg.common.BaseResponse;
import com.forty.cg.common.CodeStatus;
import com.forty.cg.config.GlobalConfiguration;
import com.forty.cg.utils.JWTUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;


@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    GlobalConfiguration globalConfiguration;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Auth-Token");
        if (token == null) {
            BaseResponse<Object> objectBaseResponse = new BaseResponse<>(CodeStatus.NOT_LOGIN);
            // 设置返回类型
            response.setContentType("application/json");
            // 返回 JSON 格式的错误信息
            response.getWriter().write(JSON.toJSONString(objectBaseResponse));
            return false;
        }
        Map<String, Object> tokenData = JWTUtils.decrypt(token, globalConfiguration.getSecretKey());
        if (tokenData == null) {
            BaseResponse<Object> objectBaseResponse = new BaseResponse<>(CodeStatus.TOKEN_UNAVAILABLE);
            response.setContentType("application/json");
            response.getWriter().write(JSON.toJSONString(objectBaseResponse));
            return false;
        }
        request.setAttribute("tokenData", tokenData);
        return true;
    }
}
