package com.forty.cg.service;

import com.forty.cg.common.BaseResponse;
import com.forty.cg.common.CodeStatus;
import com.forty.cg.schema.TokenData;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    void testWechatLogin() {
        String openid =  "oQ14G47pfy-IvmhB-AvaXqw0xlA8";
        BaseResponse<TokenData> result = userService.userLogin(openid);
        System.out.println(result);
        Assertions.assertEquals(CodeStatus.SUCCESS.getCode(),result.getCode());
    }

}
