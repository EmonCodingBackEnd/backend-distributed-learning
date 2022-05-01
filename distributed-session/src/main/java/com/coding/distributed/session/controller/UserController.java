package com.coding.distributed.session.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.coding.distributed.session.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class UserController {

    // ============================== Spring Session beg ==============================
    @GetMapping("/user/login")
    public String login(
            @RequestParam String username, @RequestParam String password, HttpSession session) {
        // 账号密码正确
        session.setAttribute(LoginInterceptor.UNAME, username);
        return "登陆成功";
    }

    @GetMapping("/user/info")
    public String info(HttpSession session) {
        // 账号密码正确
        Object username = session.getAttribute(LoginInterceptor.UNAME);
        return "当前登录的是：" + username;
    }
    // ============================== Spring Session end ==============================

    // ============================== Token Redis beg ==============================
    @Autowired private StringRedisTemplate redisTemplate;

    @GetMapping("/user/loginWithToken")
    public String loginWithToken(@RequestParam String username, @RequestParam String password) {
        // 账号密码正确
        String key = "token_" + UUID.randomUUID();

        redisTemplate.opsForValue().set(key, username, 3600, TimeUnit.SECONDS);
        return key;
    }

    @GetMapping("/user/infoWithToken")
    public String infoWithToken(@RequestParam String key) {
        // 账号密码正确
        Object username = redisTemplate.opsForValue().get(key);
        return "当前登录的是：" + username;
    }
    // ============================== Token Redis end ==============================

    // ============================== JWT beg ==============================

    @GetMapping("/user/loginWithJwt")
    public String loginWithJwt(@RequestParam String username, @RequestParam String password) {
        // 账号密码正确
        try {
            Algorithm algorithm = Algorithm.HMAC256(LoginInterceptor.JWT_KEY);
            String token =
                    JWT.create()
                            .withClaim(LoginInterceptor.UNAME, username)
                            .withClaim(LoginInterceptor.UID, 1)
                            .withExpiresAt(new Date(System.currentTimeMillis() + 300000))
                            .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
            log.error("异常", exception);
            return null;
        }
    }

    @GetMapping("/user/infoWithJwt")
    public String infoWithJwt(@RequestParam String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(LoginInterceptor.JWT_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(LoginInterceptor.UID).asString();
        } catch (JWTVerificationException exception) {
            if (exception instanceof TokenExpiredException) {
                return "已过期：" + exception.getMessage();
            } else if (exception instanceof SignatureVerificationException) {
                return "签名无效：" + exception.getMessage();
            } else if (exception instanceof JWTDecodeException) {
                return "解码失败：" + exception.getMessage();
            }
            // Invalid signature/claims
            log.error("异常", exception);
            return "异常：" + exception.getMessage();
        }
    }
    // ============================== JWT end ==============================

    @GetMapping("/user/address")
    public Integer address(@RequestAttribute Integer uid) {
        return uid;
    }
}
