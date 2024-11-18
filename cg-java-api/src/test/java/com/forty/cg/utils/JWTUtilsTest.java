package com.forty.cg.utils;

import com.forty.cg.config.GlobalConfiguration;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JWTUtilsTest {
    @Resource
    GlobalConfiguration globalConfiguration;

    @Test
    public void testEncrypt() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "123456");
        map.put("salt", "123456");
        String secretKey = globalConfiguration.getSecretKey();
        String encrypt = JWTUtils.encrypt(map, secretKey);

        System.out.println(encrypt);

    }

    @Test
    public void testDecrypt() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJmb3J0eSIsInBhc3N3b3JkIjoiMTIzNDU2Iiwic2FsdCI6IjEyMzQ1NiIsInVzZXJuYW1lIjoiYWRtaW4ifQ.ezcXK4VShhndpV-CPgbxcffOa4dNZ1cA952tp8QS45Z24aHBW5cBOdjgSlfpNnuq";
        String secretKey = globalConfiguration.getSecretKey();
        Map<String, Object> result = JWTUtils.decrypt(token, secretKey);
        System.out.println(result);
    }
}
