package com.coding.distributed.session;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public static final String JWT_KEY = "emon";
    public static final String JWT_TOKEN = "token";
    public static final String UNAME = "login_user";
    public static final String UID = "uid";

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getParameter(JWT_TOKEN);
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("token not exists!");
        }

        Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
        Integer uid;
        try {
            DecodedJWT jwt = verifier.verify(token);
            uid = jwt.getClaim(UID).asInt();
        } catch (JWTVerificationException exception) {
            if (exception instanceof TokenExpiredException) {
                throw new RuntimeException("已过期：" + exception.getMessage());
            } else if (exception instanceof SignatureVerificationException) {
                throw new RuntimeException("签名无效：" + exception.getMessage());
            } else if (exception instanceof JWTDecodeException) {
                throw new RuntimeException("解码失败：" + exception.getMessage());
            } else {
                throw new RuntimeException(exception);
            }
        }
        request.setAttribute(UID, uid);

        return super.preHandle(request, response, handler);
    }
}
